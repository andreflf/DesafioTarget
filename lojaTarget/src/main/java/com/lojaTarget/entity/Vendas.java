package com.lojaTarget.entity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendas {

	@NotBlank(message = "Nome do vendedor não pode ser nulo")
	private String vendedor;
	
	@NotBlank(message = "valor da venda não pode ser nulo")
	@Positive(message = "valor tem que ser positivo")
	private Double valor;
}
