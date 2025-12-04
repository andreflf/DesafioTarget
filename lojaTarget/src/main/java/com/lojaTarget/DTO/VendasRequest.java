package com.lojaTarget.DTO;
import java.util.List;
import com.lojaTarget.entity.Vendas;

public class VendasRequest {
	private List<Vendas> vendas;

	public List<Vendas> getVendas() {
		return vendas;
	}

	public void setVendas(List<Vendas> vendas) {
		this.vendas = vendas;
	}
}
