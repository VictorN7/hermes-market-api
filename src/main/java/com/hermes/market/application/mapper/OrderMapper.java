package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.OrderItemResponse;
import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.domain.order.Order;
import com.hermes.market.domain.order.OrderItem;

import java.util.List;

public class OrderMapper {

    private OrderMapper(){
    }

    public static OrderResponse toResponse(Order order){

        return new OrderResponse(order.getId(), UserMapper.toResponse(order.getUser()), convertItems(order.getOrderItems()), order.getStatus().name(),
                order.getTotalPrice(), order.getCreatedAt(), order.getUpdatedAt(), order.getPayment().name(), order.getDelivery().name());
    }

    public static List<OrderItemResponse> convertItems(List<OrderItem> orderItems) {
        if (orderItems == null) return List.of();
        return orderItems.stream().map(OrderItemMapper::toResponse).toList();
    }
}
