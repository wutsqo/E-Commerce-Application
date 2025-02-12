package com.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentWithBankDTO {
	private Long paymentId;
	private String paymentMethod;
    private BankDTO bank;

}
