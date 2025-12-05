package com.lojaTarget.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.lojaTarget.entity.Juros;

@Service
public class JurosService {
	
	public String calcularJuros(Juros juros) {
		
		LocalDate dataAtual = LocalDate.now();
		
		//taxa de juros que será aplicada
		BigDecimal taxaDeJuros = new BigDecimal("2.5");
		BigDecimal valor = juros.getValor();
		BigDecimal calculoJuros = BigDecimal.ZERO;
		Integer diasEmAtraso = 0;
		
		if (juros.getVencimento().isAfter(dataAtual) || juros.getVencimento().isEqual(dataAtual)) {
			return "O vencimento é hoje ou ainda não venceu.";
		}else {
		while (juros.getVencimento().isBefore(dataAtual)) {
			calculoJuros = valor.multiply(taxaDeJuros).divide(new BigDecimal("100"), 6, RoundingMode.DOWN);
			valor = valor.add(calculoJuros);
			
			juros.setVencimento(juros.getVencimento().plusDays(1));
			diasEmAtraso += 1;
		}
		
		//arredondar o valor para ficar apenas com 2 casas decimais
		valor = valor.setScale(2, RoundingMode.DOWN);
		calculoJuros = calculoJuros.setScale(2, RoundingMode.DOWN);
		
		return "Dias em atraso: " +diasEmAtraso+ "\nValor do juros (2,5% ao dia): R$" + calculoJuros + "\nValor total com juros aplicado: R$"  + valor;
		}
	}
}
