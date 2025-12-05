package com.lojaTarget.service;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.lojaTarget.entity.Juros;

@Service
public class JurosService {
	
	public String calcularJuros(Juros juros) {
		
		LocalDate dataAtual = LocalDate.now();
		Double taxaDeJuros = 2.5;
		
		if(juros.getVencimento().getDayOfYear() >= dataAtual.getDayOfYear()) {
			return "O vencimento é hoje ou ainda não venceu.";
		}else {
		while (juros.getVencimento().getDayOfYear() < dataAtual.getDayOfYear()) {
			juros.setValor(juros.getValor() + (juros.getValor() * taxaDeJuros)/100);
			juros.setVencimento(juros.getVencimento().plusDays(1));
		}
		return "O valor do juros (sendo 2,5% ao dia) é de R$: " + juros.getValor();
		}
	}
}
