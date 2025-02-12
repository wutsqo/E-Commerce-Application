package com.app.payloads;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderWithBankDTO {
	
	private Long orderId;
	private String email;
	private List<OrderItemDTO> orderItems = new ArrayList<>();
	private LocalDate orderDate;
	private PaymentWithBankDTO payment;
	private Double totalAmount;
	private String orderStatus;
    private BankDTO bank;
}
