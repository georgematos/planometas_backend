package br.com.zipext.plr.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.zipext.plr.dto.FolhaMetaDTO;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.service.FolhaMetaService;

@Controller
@RequestMapping("/metas")
public class FolhaMetaController {

	@Autowired
	private FolhaMetaService service;
	
	@GetMapping("/colaborador/{matricula}")
	public ResponseEntity<List<FolhaMetaDTO>> findByColaborador(@PathVariable("matricula") String matricula) {
		List<FolhaMetaDTO> dtos = this.service.findByColaborador(new ColaboradorModel(matricula)).stream()
				.map(FolhaMetaDTO::new)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<FolhaMetaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/responsavel/{matricula}")
	public ResponseEntity<List<FolhaMetaDTO>> findByResponsavel(@PathVariable("matricula") String matricula) {
		List<FolhaMetaDTO> dtos = this.service.findByResponsavel(new ColaboradorModel(matricula)).stream()
				.map(FolhaMetaDTO::new)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<FolhaMetaDTO>>(dtos, HttpStatus.OK);
	}
}
