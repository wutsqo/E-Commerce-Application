package com.app.entites;

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
@Table(name = "banks")
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bankId;

	@NotBlank
	private String bankName;

    @NotBlank
    private String accountNumber;

}
