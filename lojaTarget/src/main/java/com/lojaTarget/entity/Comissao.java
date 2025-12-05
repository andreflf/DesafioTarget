package com.lojaTarget.entity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comissao {

	private String vendedor;
	private BigDecimal valorDaComissao;
	private String motivoDaComissao;
}
