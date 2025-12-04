package com.lojaTarget.DTO;
import java.util.List;
import com.lojaTarget.entity.Estoque;

public class EstoqueRequest {
	
	private List<Estoque> estoque;
	
	public List<Estoque> getEstoque(){
		return estoque;
	}
	
	public void setEstoque(List<Estoque> estoque) {
		this.estoque = estoque;
	}
}
