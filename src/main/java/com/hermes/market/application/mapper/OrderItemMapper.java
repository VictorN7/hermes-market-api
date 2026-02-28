package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.OrderItemResponse;
import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.domain.order.OrderItem;

import java.math.BigDecimal;

public class OrderItemMapper {

    private OrderItemMapper(){
    }

    public static OrderItemResponse toResponse(OrderItem orderItem){
        return new OrderItemResponse(orderItem.getId(), ProductMapper.toResponse(orderItem.getProduct()), OrderMapper.toResponse(orderItem.getOrder()),
                orderItem.getQuantity(), orderItem.getPrice());

    }
}
