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
import com.lojaTarget.DTO.EstoqueRequest;
import com.lojaTarget.entity.Estoque;
import com.lojaTarget.enums.TipoDeMovimentacaoEstoque;
import com.lojaTarget.service.EstoqueService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/target")
public class LojaTargetEstoqueController {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@GetMapping("/estoque")
	public ResponseEntity<Estoque> movimentarEstoque(@RequestParam Long codigoProduto, @RequestParam Integer quantidade, @RequestParam TipoDeMovimentacaoEstoque descricao) {
		try {
			Estoque estoque = this.estoqueService.movimentarEstoque(codigoProduto, quantidade, descricao);
			return new ResponseEntity<Estoque>(estoque,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/salvarEstoque")
	public ResponseEntity<String> salvarEstoque(@Valid @RequestBody EstoqueRequest estoqueRequest) {
		try {
			List<Estoque> estoque = estoqueRequest.getEstoque();
			String retorno = this.estoqueService.salvarEstoque(estoque);
			return new ResponseEntity<String>(retorno, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
