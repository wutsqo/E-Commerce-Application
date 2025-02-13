package com.app.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponId;

	@NotBlank
    @Column(unique = true, nullable = false)
	private String couponCode;

    @Column(nullable = false)
    private double discountAmount;
}