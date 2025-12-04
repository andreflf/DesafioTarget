package com.lojaTarget.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lojaTarget.DTO.VendasRequest;
import com.lojaTarget.entity.Comissao;
import com.lojaTarget.entity.Vendas;
import com.lojaTarget.service.LojaTargetService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/target")
public class LojaTargetController {
	
	@Autowired
	private LojaTargetService lojaTargetService;
	
	@GetMapping("/comissaoPorVenda")
	public ResponseEntity<List<Comissao>> calculaComissaoPorVenda(@Valid @RequestBody VendasRequest request) {
		try {
			List<Vendas> vendas = request.getVendas();
			List<Comissao> comissao = this.lojaTargetService.calculaComissaoPorVenda(vendas);
			return new ResponseEntity<List<Comissao>>(comissao, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/comissaoPorVendedor")
	public ResponseEntity<Comissao> calculaComissaoPorVendedor(@Valid @RequestBody VendasRequest request, @RequestParam String vendedor){
		try {
			List<Vendas> vendas = request.getVendas();
			Comissao comissao = this.lojaTargetService.calculaComissaoPorVendedor(vendas, vendedor);
			return new ResponseEntity<>(comissao, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/salvarVendas")
	public ResponseEntity<String> salvarVendas(@Valid @RequestBody VendasRequest request) {
		try {
			List<Vendas> vendas = request.getVendas();
			String mensagem = this.lojaTargetService.salvarVendas(vendas);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao salvar", HttpStatus.BAD_REQUEST);
		}
	}
}
