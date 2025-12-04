package com.lojaTarget.entity;
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
	private Double valorDaComissao;
	private String motivoDaComissao;
}
