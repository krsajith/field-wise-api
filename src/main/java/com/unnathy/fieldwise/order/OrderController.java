package com.unnathy.fieldwise.order;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.orderview.OrderViewDTO;
import com.unnathy.fieldwise.service.OrderPdfService;
import com.unnathy.fieldwise.user.User;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController implements BaseController<OrderDTO, OrderDTO, Long> {

    private final OrderService service;
    private final OrderPdfService pdfService;

    @Override
    public BasicEntityService<OrderDTO, OrderDTO, Long> getService() {
        return service;
    }

    @GetMapping("/view")
    public Page<OrderViewDTO> getOrderView(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        return service.getOrderView(page, size);
    }

    @GetMapping("/view/user/{userId}")
    public Page<OrderViewDTO> getOrderViewByUserId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable Long userId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        return service.getOrderViewByUserId(userId, page, size);
    }

    @GetMapping("/view/{id}")
    public OrderViewDTO getOrderViewById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable Long id) throws UnnathyError {
        return service.getOrderViewById(id);
    }

    @GetMapping("/status/{status}")
    public List<OrderDTO> getOrdersByStatus(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable String status) {
        return service.getOrdersByStatus(status);
    }

    @PostMapping("/master")
    public OrderDTO createOrderWithItems(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @RequestBody OrderWithItemsDTO payload) throws UnnathyError {
        return service.postWithItems(payload, authorization, principal);
    }

    @PutMapping("/master")
    public OrderDTO updateOrderWithItems(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @RequestBody OrderWithItemsDTO payload) throws UnnathyError {
        return service.putWithItems(payload, authorization, principal);
    }

    @GetMapping("/pdf/{orderId}")
    public ResponseEntity<byte[]> downloadOrderPdf(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable Long orderId) throws UnnathyError {
        byte[] pdfBytes = pdfService.generateOrderPdf(orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "order-" + orderId + ".pdf");
        headers.setContentLength(pdfBytes.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}



