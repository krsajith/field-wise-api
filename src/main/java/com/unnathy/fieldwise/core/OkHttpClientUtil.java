package com.unnathy.fieldwise.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.unnathy.fieldwise.common.domain.HttpMethod;
import com.unnathy.fieldwise.common.domain.TaomishError;
import com.unnathy.fieldwise.security.EncryptionUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Nullable;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static com.unnathy.fieldwise.common.constants.HttpClientConstants.*;
import static com.unnathy.fieldwise.core.ExceptionTranslator.REQUEST_ID;

@Service
@Slf4j
@Getter
public class OkHttpClientUtil {

    @Value("${trustAllCertificates:true}")
    private boolean trustAllCertificates = true;

    private final OkHttpClient okHttpClient;

    public OkHttpClientUtil() {
        this.okHttpClient = getOkHttpClientBuilder().readTimeout(Duration.ZERO).build();
    }

    public OkHttpClient.Builder getOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (trustAllCertificates) {
            configureTrustAllCertificates(builder);
        }

        return builder;
    }

    @SneakyThrows
    private void configureTrustAllCertificates(OkHttpClient.Builder builder) {
        try {
            // Create a trust manager that trusts all certificates
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                            // Empty - trust everything
                            log.debug("Trusting client certificate: {}", authType);
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                            // Empty - trust everything
                            log.debug("Trusting server certificate: {}", authType);
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // Install the trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            builder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true);

            log.info("Configured OkHttpClient with trust-all certificates");
        } catch (Exception e) {
            log.error("Failed to configure trust-all certificates", e);
            throw new RuntimeException("Failed to configure SSL context", e);
        }
    }



    @Nullable
    public  <T> T  get(String url, String token, Class<T> type) {
        try( var response = queryForResponse(url, HttpMethod.GET,token, null)) {
            assert response != null;
            var stringResponse = Objects.requireNonNull(response.body()).string();
            if (stringResponse.isEmpty()) {
                return null;
            }
            return type.cast(JsonUtils.getObjectMapper().convertValue(stringResponse, type));
        } catch (Exception e) {
            log.error("Error calling {}",url,e);
            e.printStackTrace();
        }
        return null;
    }

    public Response queryForResponse(String url, HttpMethod httpMethod, String token, Object payload) throws TaomishError {
        String finalUrl = buildUrl(url);
        var requestBuilder = new Request.Builder().url(finalUrl).
                addHeader("Content-Type", APPLICATION_JSON);
        if (token != null) {
            requestBuilder.addHeader(AUTHORIZATION, token);
        }
        requestBuilder.addHeader("X-hguyt-sdfds", EncryptionUtil.getEncryptedHeaderValue());
        String requestId = MDC.get(REQUEST_ID);
        if(requestId!=null){
            requestBuilder.addHeader(REQUEST_ID, requestId);
        }
        return callApi(url,httpMethod,requestBuilder,payload);
    }

    private Response callApi(String url, HttpMethod httpMethod, Request.Builder requestBuilder, Object payload) {
        try {
            if(httpMethod == HttpMethod.POST) {
                var body = RequestBody.create(Objects.requireNonNull(JsonUtils.toJsonString(payload)), okhttp3.MediaType.parse(APPLICATION_JSON));
                requestBuilder = requestBuilder.post(body);
            } else if(httpMethod == HttpMethod.PUT) {
                var body = RequestBody.create(Objects.requireNonNull(JsonUtils.toJsonString(payload)), okhttp3.MediaType.parse(APPLICATION_JSON));
                requestBuilder = requestBuilder.put(body);
            } else if(httpMethod == HttpMethod.DELETE) {
                requestBuilder = requestBuilder.delete();
            } else {
                requestBuilder = requestBuilder.get();
            }
            return okHttpClient.newCall(requestBuilder.build()).execute();
        } catch (Exception e) {
            log.error("Error executing {},{},{}", url, payload, ExceptionUtils.getRootCause(e).getMessage());
        }
        return null;
    }

    public <T> List<T> queryForList(String url, HttpMethod httpMethod,String token, Object payload, Class<T> returnType) throws TaomishError {
        try (var response = queryForResponse(url, httpMethod, token, payload)) {
            assert response != null;
            var output = Objects.requireNonNull(response.body()).string();
            return JsonUtils.getObjectMapper().readValue(output,JsonUtils.getObjectMapper().getTypeFactory().constructCollectionType(List.class,returnType));
        } catch (Exception e) {
            log.error("Error converting {},{}, {}", url, payload, ExceptionUtils.getRootCause(e).getMessage());
            throw new TaomishError("TXN-UTIL-02 (URL : "+url+")");
        }
    }

    public <T> T query(String url,HttpMethod httpMethod, String token, Object payload, Class<T> returnType) throws TaomishError {
        try (var response = queryForResponse(url, httpMethod, token, payload)) {
            assert Objects.requireNonNull(response).body() != null;
            var stringResponse = response.body().string();
            if (stringResponse.isEmpty()) {
                return null;
            }
            return JsonUtils.convertToClass(stringResponse, returnType);
        } catch (Exception e) {
            log.error("Error calling api {}, {}, {}", url, payload, ExceptionUtils.getRootCause(e).getMessage());
            throw new TaomishError("TXN-UTIL-02 (URL : "+url+")");
        }
    }

    private String buildUrl(String url) {
        try {
            if(url.indexOf('?')<0) return url;
            URL aURL = new URL(url);
            Matcher matcher = PATTERN_QUERY_PARAM.matcher(url);
            var params = new ArrayList<Triple<String,Integer,Integer>>();
            String name = null;
            var start = 0;
            var end = 0;
            while (matcher.find()) {
                if(name!=null){
                    end=matcher.start();
                    params.add(Triple.of(name,start,end));
                }
                start=matcher.end();
                name= matcher.group().substring(1,matcher.group().length()-1);
            }
            params.add(Triple.of(name,start,url.length()));
            HttpUrl.Builder urlBuilder
                    = Objects.requireNonNull(HttpUrl.parse(String.format("%s://%s%s%s", aURL.getProtocol(), aURL.getHost(), aURL.getPort() <= 0 ? "" : ":" + aURL.getPort(), aURL.getPath()))).newBuilder();
            for (var param:params){
                urlBuilder.addQueryParameter(param.getLeft(), url.substring(param.getMiddle(),param.getRight()));
            }
            return urlBuilder.build().toString();
        } catch (Exception e) {
            return url;
        }
    }

    public Response queryURlEncoded(String url,Map<String,Object> params) throws TaomishError {
            if (params == null || params.isEmpty()) {
                throw new TaomishError("TXN-UTIL-03: Empty or null parameters are not allowed. (URL: " + url + ")");
            }

            String queryString = params.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue().toString())
                    .collect(Collectors.joining("&"));
            return queryURlEncoded(url,queryString);
    }

    public Response queryURlEncoded(String url,String queryString) throws TaomishError {
        try {
            MediaType mediaType = MediaType.parse(APPLICATION_URL_ENCODED);
            RequestBody body = RequestBody.create(mediaType, queryString.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Content-Type", APPLICATION_URL_ENCODED)
                    .build();
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new TaomishError("TXN-UTIL-02 (URL : "+url+")");
        }
    }
    public Response queryMultipart(String url, String token, Map<String,Object> params, MultipartFile multipartFile) throws TaomishError {
        try {
            RequestBody fileBody = RequestBody.create(multipartFile.getBytes(),
                    MediaType.parse(Objects.requireNonNull(multipartFile.getContentType())) != null ?
                            MediaType.parse(multipartFile.getContentType())
                            : MediaType.parse("application/octet-stream")
            );
            var bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            params.forEach((key, value) -> bodyBuilder.addFormDataPart(key, value.toString()));
            var body = bodyBuilder.addFormDataPart("file", multipartFile.getOriginalFilename(), fileBody).build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Content-Type", APPLICATION_URL_ENCODED)
                    .addHeader("X-hguyt-sdfds", EncryptionUtil.getEncryptedHeaderValue())
                    .addHeader("Authorization", token)
                    .build();
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new TaomishError("TXN-UTIL-02 (URL : "+url+")",e);
        }
    }
    public <T> T queryPage(String url,HttpMethod httpMethod, String token, Object payload, TypeReference<T> typeReference) throws TaomishError, IOException {
        var response = queryForResponse(url,httpMethod,token,payload);
        if(response == null || !response.isSuccessful()) {
            throw new TaomishError("Failed to get Record.");
        }
        String responseString = Objects.requireNonNull(response.body()).string();
        return JsonUtils.getObjectMapper().readValue(responseString,typeReference);
    }

}
