package br.com.zipext.plr.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.MetasDTO;
import br.com.zipext.plr.service.ColaboradorMetasGeraisService;

@RestController
@RequestMapping("/metasGerais")
public class MetasGeraisController {
	
	@Autowired
	private ColaboradorMetasGeraisService service;
	@GetMapping
	public ResponseEntity<List<MetasDTO>> findAll() {
		return new ResponseEntity<>(this.service.findAll()
				.stream()
				.map(MetasDTO::new)
				.collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/{matricula}")
	public ResponseEntity<MetasDTO> findByMatricula(@PathVariable("matricula") String matricula) {
		
		return new ResponseEntity<>(new MetasDTO(this.service.findByMatricula(matricula)), HttpStatus.OK);
	}
}
