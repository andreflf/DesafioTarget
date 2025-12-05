package com.lojaTarget.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	
	//valores que são usados na regra de negócio
	 private BigDecimal cem = new BigDecimal("100");
	 private BigDecimal quinhetos = new BigDecimal("500");
	 private BigDecimal umPorcento = new BigDecimal("0.01");
	 private BigDecimal cincoPorcento = new BigDecimal("0.05");

	public List<Comissao> calculaComissaoPorVenda(List<Vendas> vendas) {
		List<Comissao> comissoes = new ArrayList<>();

		//regras de negócio das comissoes
		for (Vendas venda : vendas) {
			Comissao comissao = new Comissao();
			BigDecimal valor = venda.getValor();
			
			// Vendas abaixo de R$100,00 não gera comissão
			if (valor.compareTo(cem) < 0) {
				comissao.setVendedor(venda.getVendedor());
				comissao.setValorDaComissao(new BigDecimal(0));
				comissao.setMotivoDaComissao("Descrição: o valor da venda foi de R$" +valor+ " - abaixo de R$100,00 não gera comissão");
				comissoes.add(comissao);
			}

			// Vendas abaixo de R$500,00 gera 1% de comissão
			if (valor.compareTo(cem) >= 0 && valor.compareTo(quinhetos) < 0) {
				BigDecimal valorComissao = valor.multiply(umPorcento).setScale(2, RoundingMode.DOWN);
				
				comissao.setVendedor(venda.getVendedor());
				comissao.setValorDaComissao(valorComissao);
				comissao.setMotivoDaComissao("Descrição: o valor da venda foi de R$" +venda.getValor()+ " - abaixo de R$500,00 gera 1% de comissão");
				comissoes.add(comissao);
			}

			// A partir de R$500,00 gera 5% de comissão
			if(valor.compareTo(quinhetos) >= 0) {
				BigDecimal valorComissao = valor.multiply(cincoPorcento).setScale(2, RoundingMode.DOWN);
				
				comissao.setVendedor(venda.getVendedor());
				comissao.setValorDaComissao(valorComissao);
				comissao.setMotivoDaComissao("Descrição: o valor da venda foi de R$" +venda.getValor()+ " - A partir de R$500,00 gera 5% de comissão");
				comissoes.add(comissao);
			}
		}

		return comissoes;
	}
	
	public Comissao calculaComissaoPorVendedor(List<Vendas> vendas, String vendedor){
		Comissao comissao = new Comissao();
		comissao.setVendedor(vendedor);
		comissao.setMotivoDaComissao("Descrição: o valor total de comissão do vendedor com base nas regras: abaixo de R$100,00 não gera comissão, "
				+ "abaixo de R$500,00 gera 1% de comissão e a partir de R$500,00 gera 5% de comissão.");
		BigDecimal valorDasComissoes = BigDecimal.ZERO;
		
		for (Vendas venda : vendas) {	
			if(venda.getVendedor().equals(vendedor)) {
				BigDecimal valor = venda.getValor();
				
			// Vendas abaixo de R$100,00 não gera comissão, ou seja não contabiliza nada.
			// Vendas abaixo de R$500,00 gera 1% de comissão
			if (valor.compareTo(cem) >= 0 && valor.compareTo(quinhetos) < 0) {
				valorDasComissoes = valorDasComissoes.add(valor.multiply(umPorcento).setScale(2, RoundingMode.DOWN));
			}

			// A partir de R$500,00 gera 5% de comissão
			if(valor.compareTo(quinhetos) >= 0) {
				valorDasComissoes = valorDasComissoes.add(valor.multiply(cincoPorcento).setScale(2, RoundingMode.DOWN));
			}
		}
		}
		
		comissao.setValorDaComissao(valorDasComissoes);
		if(valorDasComissoes.compareTo(BigDecimal.ZERO) == 0) {
			comissao.setMotivoDaComissao("Descrição: o nome do vendedor é inválido ou o vendedor não teve nenhuma venda com direito a comissão");
		}
		return comissao;
	}

	public String salvarVendas(List<Vendas> vendas) {
		this.lojaTargetRepository.saveAll(vendas);
		return "Vendas salvas com sucesso";
	}

}
