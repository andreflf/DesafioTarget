package com.lojaTarget.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estoque {
	
	@Id
	private Long codigoProduto;
	
	@NotBlank(message = "Descricão do produto não pode ser nulo")
	private String descricaoProduto;
	
	@NotNull(message = "estoque não pode ser nulo")
	@PositiveOrZero(message = "quantidade em estoque tem que ser positivo")
	private Integer estoque;

}
