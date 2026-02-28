package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.OrderItemResponse;
import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.domain.order.Order;
import com.hermes.market.domain.order.OrderItem;
import com.hermes.market.domain.user.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    private OrderMapper(){

    }

    public static OrderResponse toResponse(Order order){

        return new OrderResponse(order.getId(), UserMapper.toResponse(order.getUser()), convertItems(order.getOrderItens()), order.getStatus().name(),
                order.getTotalPrice(), order.getCreatedAt(), order.getUpdatedAt(), order.getPayment().name(), order.getDelivery().name());
    }

    public static List<OrderItemResponse> convertItems(List<OrderItem> orderItems) {

        if (orderItems == null) return List.of();

        return orderItems.stream()
                .map(x -> new OrderItemResponse(
                        x.getId(),
                        ProductMapper.toResponse(x.getProduct()),
                        x.getQuantity(),
                        x.getPrice()
                ))
                .toList();
    }
}
