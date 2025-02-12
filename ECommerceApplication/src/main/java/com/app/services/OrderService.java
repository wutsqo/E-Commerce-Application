package com.app.services;

import java.util.List;

import com.app.payloads.OrderDTO;
import com.app.payloads.OrderResponse;
import com.app.payloads.OrderWithBankDTO;

public interface OrderService {
	
	OrderDTO placeOrder(String email, Long cartId, String paymentMethod);

	OrderWithBankDTO placeOrder(String email, Long cartId, String paymentMethod, Long bankId);
	
	OrderDTO getOrder(String email, Long orderId);
	
	List<OrderDTO> getOrdersByUser(String email);
	
	OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	OrderDTO updateOrder(String email, Long orderId, String orderStatus);
}
