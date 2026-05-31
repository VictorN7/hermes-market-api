package com.hermes.market.web.controller.admin;

import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.application.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/orders")
@Validated
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok().body(orderService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(orderService.findById(id));
    }

    @GetMapping("/inactive-users")
    public ResponseEntity<Page<OrderResponse>> findAllByUserInactive(Pageable pageable){
        return ResponseEntity.ok().body(orderService.findOrdersByInactiveUser(pageable));
    }

    @GetMapping("/inactive-users/{id}")
    public ResponseEntity<OrderResponse> findByIdInactiveUser(@PathVariable Long id){
        return ResponseEntity.ok().body(orderService.findOrderByInactiveUser(id));
    }

}
