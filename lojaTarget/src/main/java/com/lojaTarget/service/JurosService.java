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
		
		if (juros.getVencimento().isAfter(dataAtual) || juros.getVencimento().isEqual(dataAtual)) {
			return "O vencimento é hoje ou ainda não venceu.";
		}else {
		while (juros.getVencimento().isBefore(dataAtual)) {
			BigDecimal calculoJuros = valor.multiply(taxaDeJuros).divide(new BigDecimal("100"), 6, RoundingMode.HALF_UP);
			valor = valor.add(calculoJuros);
			
			juros.setVencimento(juros.getVencimento().plusDays(1));
			
		}
		
		//arredondar o valor para ficar apenas com 2 casas decimais
		valor = valor.setScale(2, RoundingMode.DOWN);
		
		return "O valor do juros (sendo 2,5% ao dia) é de R$: " + valor;
		}
	}
}
