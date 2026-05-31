package com.hermes.market.web.controller.system;

import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.application.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/orders")
@Validated
public class InternalOrderController {

    private final OrderService orderService;

    public InternalOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<OrderResponse> payOrder(@PathVariable Long id){
        return ResponseEntity.ok().body(orderService.payOrder(id));
    }

    @PatchMapping("/{id}/ship")
    public ResponseEntity<OrderResponse> shipOrder(@PathVariable Long id){
        return ResponseEntity.ok().body(orderService.shipOrder(id));
    }

    @PatchMapping("/{id}/deliver")
    public ResponseEntity<OrderResponse> deliverOrder(@PathVariable Long id){
        return ResponseEntity.ok().body(orderService.deliverOrder(id));
    }

}
