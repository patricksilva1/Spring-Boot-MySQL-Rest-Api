package dev.patricksilva.crud.models.services;

import dev.patricksilva.crud.models.shared.OrderDTO;

import java.util.List;

public interface OrderService {

     List<OrderDTO> getAllOrders();

     OrderDTO getOrderById(Long id);

     OrderDTO createOrder(OrderDTO orderDTO);

     void deleteOrder(Long id);
}