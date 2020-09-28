package br.com.zipext.plr.controller.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.GenericDTO;
import br.com.zipext.plr.service.AvaliacaoProjetoPrazoService;

@Controller
@RequestMapping("/avalprazo")
public class AvaliacaoProjetoPrazoController {

	@Autowired
	private AvaliacaoProjetoPrazoService service;
	
	@GetMapping
	public ResponseEntity<List<GenericDTO>> findAll() {
		return
				new ResponseEntity<>(this.service.findAllOrderedByDescricao().stream()
						.map(m -> new GenericDTO(m)).collect(Collectors.toList()), HttpStatus.OK);
	}
}
