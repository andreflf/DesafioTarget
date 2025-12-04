package com.lojaTarget.entity;
import com.lojaTarget.enums.TipoDeMovimentacaoEstoque;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class MovimentacaoEstoque {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "quantidade não pode ser nulo")
	@PositiveOrZero(message = "quantidade tem que ser positivo")
	private Integer quantidade;
	
	@Enumerated  //apenas duas opçoes: Entrada ou Saida de produtos.
	@Column(nullable = false)
	private TipoDeMovimentacaoEstoque descricao;
	
	//várias movimentaçoes podem estar atreladas a um mesmo produto
	@ManyToOne
	private Estoque estoque;

}
