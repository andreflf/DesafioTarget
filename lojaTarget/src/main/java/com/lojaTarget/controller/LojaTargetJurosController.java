package com.lojaTarget.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lojaTarget.entity.Juros;
import com.lojaTarget.service.JurosService;

@RestController
@RequestMapping("/api/target")
public class LojaTargetJurosController {

	@Autowired
	private JurosService jurosService;

	@GetMapping("/juros")
	public ResponseEntity<String> calcularJuros(@RequestBody Juros juros) {
		try {
			String retorno = this.jurosService.calcularJuros(juros);
			return new ResponseEntity<String>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao calcular", HttpStatus.BAD_REQUEST);
		}
	}
}
