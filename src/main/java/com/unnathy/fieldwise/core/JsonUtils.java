package com.unnathy.fieldwise.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;



import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = buildObjectMapper();

    public static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//        objectMapper.registerModule(new SpringDataJackson3Configuration.PageModule());
        final SimpleModule localDateTimeSerialization = new SimpleModule();
        localDateTimeSerialization.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        localDateTimeSerialization.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        objectMapper.registerModule(localDateTimeSerialization);
        return objectMapper;
    }

    public static String toJsonString(Object obj){
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e){
            log.debug("Error serializing {}",obj,e);
        }
        return null;
    }

    @SneakyThrows
    public static <T> T getResourceFromFile(String path,Class<T> tClass) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:" + path);
        if(resource.exists()) {
            var inputStream = resource.getInputStream();
            return getObjectMapper().readValue(inputStream, new TypeReference<>() {
            });
        }
        throw new UnnathyError("Resource Not Found","",null);
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static <T> T convertToClass(String response, Class<T> returnClass) {
        try {
            return convert(response, returnClass);
        } catch (Exception e) {
            log.error("Error converting {} to {}, {}", response, returnClass.getSimpleName(), ExceptionUtils.getRootCause(e).getMessage());
            return returnClass.cast(response);
        }
    }

    @SneakyThrows
    public static  <T> T convert(String string, Class<T> toObjectOf) {
        try {
            return JsonUtils.getObjectMapper().readValue(string, toObjectOf);
        } catch (JsonProcessingException e) {
            log.error("Error converting {},{}", string, ExceptionUtils.getRootCause(e).getMessage());
            try {
                return JsonUtils.getObjectMapper().convertValue(string, toObjectOf);
            } catch (Exception ex) {
                log.error("Error converting {}, {}, {}", string, string, ExceptionUtils.getRootCause(ex).getMessage());
                return JsonUtils.getObjectMapper().convertValue(getFromValue(string),toObjectOf);
            }
        }

    }

    @SneakyThrows
    private static Object getFromValue(String string) {
        try {
            return JsonUtils.getObjectMapper().readValue(string, Map.class);
        } catch (JsonProcessingException e) {
            log.error("Error converting {} {} {}", string, string, ExceptionUtils.getRootCause(e).getMessage());
            return JsonUtils.getObjectMapper().readValue(string, List.class);
        }
    }
}

class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.format(format));
    }
}

class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private final DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext context) throws IOException {
        try {
            return parseLocalDateTime(p);
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDateTime parseLocalDateTime(JsonParser p) throws Exception {
        try {
            return LocalDateTime.parse(p.getValueAsString(), fmt);
        } catch (Exception e) {
            return LocalDateTime.parse(p.getValueAsString(), fmt2);
        }
    }
}
