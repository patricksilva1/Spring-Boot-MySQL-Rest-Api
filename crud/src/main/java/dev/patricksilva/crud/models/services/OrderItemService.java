package dev.patricksilva.crud.models.services;

import dev.patricksilva.crud.models.entities.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getAllOrderItems();

    OrderItem getOrderItemById(Long id);

    OrderItem createOrderItem(OrderItem orderItem);

    void deleteOrderItem(Long id);
}
