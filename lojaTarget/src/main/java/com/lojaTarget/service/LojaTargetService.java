package com.lojaTarget.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojaTarget.entity.Comissao;
import com.lojaTarget.entity.Vendas;
import com.lojaTarget.repository.LojaTargetRepository;

@Service
public class LojaTargetService {

	@Autowired
	private LojaTargetRepository lojaTargetRepository;

	public List<Comissao> calculaComissaoPorVenda(List<Vendas> vendas) {
		Comissao comissao = new Comissao();
		List<Comissao> comissoes = new ArrayList<>();

		//regras de negócio das comissoes
		for (Vendas venda : vendas) {
			
			// Vendas abaixo de R$100,00 não gera comissão
			if (venda.getValor() < 100) {
				comissao.setNome(venda.getVendedor());
				comissao.setValorDaComissao(0.00);
				comissao.setMotivoDaComissao("Descrição: o valor da venda foi de R$" +venda.getValor()+ " - abaixo de R$100,00 não gera comissão");
				comissoes.add(comissao);
			}

			// Vendas abaixo de R$500,00 gera 1% de comissão
			if (venda.getValor() >= 100 && venda.getValor() < 500) {
				comissao.setNome(venda.getVendedor());
				comissao.setValorDaComissao(venda.getValor()/100);
				comissao.setMotivoDaComissao("Descrição: o valor da venda foi de R$" +venda.getValor()+ " - abaixo de R$500,00 gera 1% de comissão");
				comissoes.add(comissao);
			}

			// A partir de R$500,00 gera 5% de comissão
			if(venda.getValor() >= 500) {
				comissao.setNome(venda.getVendedor());
				comissao.setValorDaComissao((venda.getValor()* 5)/100);
				comissao.setMotivoDaComissao("Descrição: o valor da venda foi de R$" +venda.getValor()+ " - A partir de R$500,00 gera 5% de comissão");
				comissoes.add(comissao);
			}
		}

		return comissoes;
	}
	
	public Comissao calculaComissaoPorVendedor(List<Vendas> vendas, String vendedor){
		Comissao comissao = new Comissao();
		comissao.setNome(vendedor);
		comissao.setMotivoDaComissao("Descrição: o valor total de comissão com base nas regras (abaixo de R$100,00 não gera comissão, +"
				+ "abaixo de R$500,00 gera 1% de comissão e a partir de R$500,00 gera 5% de comissão)");
		Double valorDasComissoes = 0.00;
		
		for (Vendas venda : vendas) {
			if(venda.getVendedor().equals(vendedor)) {
			// Vendas abaixo de R$100,00 não gera comissão, ou seja não contabiliza nada.
			// Vendas abaixo de R$500,00 gera 1% de comissão
			if (venda.getValor() >= 100 && venda.getValor() < 500) {
				valorDasComissoes += venda.getValor()/100;
			}

			// A partir de R$500,00 gera 5% de comissão
			if(venda.getValor() >= 500) {
				comissao.setNome(venda.getVendedor());
				valorDasComissoes += ((venda.getValor()* 5)/100);
			}
		}
		}
		
		comissao.setValorDaComissao(valorDasComissoes);
		if(comissao.getValorDaComissao() == 0) {
			comissao.setMotivoDaComissao("Descrição: o nome do vendedor é inválido ou o vendedor não teve nenhuma venda com direito a comissão");
		}
		return comissao;
	}

	public String salvaVendas(Vendas vendas) {
		this.lojaTargetRepository.save(vendas);
		return "Vendas salvas com sucesso";
	}

}
