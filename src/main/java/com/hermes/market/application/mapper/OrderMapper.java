package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.request.OrderRequest;
import com.hermes.market.application.dto.response.OrderItemResponse;
import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.application.dto.response.OrderSummaryResponse;
import com.hermes.market.domain.order.DeliveryMethod;
import com.hermes.market.domain.order.Order;
import com.hermes.market.domain.order.OrderItem;
import com.hermes.market.domain.order.PaymentMethod;
import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.User;

import java.util.List;

public class OrderMapper {

    private OrderMapper(){
    }

    public static OrderResponse toResponse(Order order){

        return new OrderResponse(order.getId(), UserMapper.toResponse(order.getUser()), convertItems(order.getOrderItems()), order.getStatus().name(),
                order.getTotalPrice(), order.getCreatedAt(), order.getUpdatedAt(), order.getPayment().name(), order.getDelivery().name(), AddressMapper.toResponse(order.getAddress()));
    }

    public static OrderSummaryResponse toSummary(Order order){
        return new OrderSummaryResponse(order.getId(), order.getStatus().name(),order.getTotalPrice(),order.getCreatedAt(),
                order.getPayment().name(), order.getDelivery().name());
    }

    public static List<OrderItemResponse> convertItems(List<OrderItem> orderItems) {
        if (orderItems == null) return List.of();
        return orderItems.stream().map(OrderItemMapper::toResponse).toList();
    }

    public static Order toCreate(PaymentMethod paymentMethod, DeliveryMethod deliveryMethod, User user, Address address){
        return new Order(user, paymentMethod, deliveryMethod, address);
    }

}
