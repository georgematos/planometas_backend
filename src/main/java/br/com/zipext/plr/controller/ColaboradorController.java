package br.com.zipext.plr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.ColaboradorDTO;
import br.com.zipext.plr.service.ColaboradorService;

@Controller
@RequestMapping("/colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorService service;
	
	@GetMapping("/{matricula}")
	public ResponseEntity<ColaboradorDTO> findByMatricula(@PathVariable("matricula") String matricula) {
		return new ResponseEntity<ColaboradorDTO>(new ColaboradorDTO(this.service.findByMatricula(matricula)), HttpStatus.OK);
	}
}
