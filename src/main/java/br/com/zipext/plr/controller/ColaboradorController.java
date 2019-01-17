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

import br.com.zipext.plr.dto.ColaboradorDTO;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.service.ColaboradorService;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
	
	@Autowired
	private ColaboradorService service;
	
	@GetMapping
	public ResponseEntity<List<ColaboradorDTO>> findAll() {
		return new ResponseEntity<>(this.service.findAll().stream().map(ColaboradorDTO::new).collect(Collectors.toList()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/{matricula}")
	public ResponseEntity<ColaboradorDTO> findColaborador(@PathVariable("matricula") String matricula) {
		ColaboradorModel model = this.service.findByMatricula(matricula);
		return new ResponseEntity<>(new ColaboradorDTO(model), HttpStatus.OK);
	}
}
