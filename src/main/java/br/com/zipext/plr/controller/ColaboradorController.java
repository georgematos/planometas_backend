package br.com.zipext.plr.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.ColaboradorDTO;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.service.ColaboradorService;
import br.com.zipext.plr.utils.PLRUtils;

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
	public ResponseEntity<ColaboradorDTO> findColaborador(@PathVariable("matricula") String matricula, 
			@RequestParam(name = "filterVersion", required = false) Long filterVersion) {
		ColaboradorModel model = this.service.findByMatricula(matricula);
		ColaboradorDTO dto;
		if (filterVersion != null) {
			dto = new ColaboradorDTO(model, filterVersion);
		} else {
			dto = new ColaboradorDTO(model);
		}
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/{matricula}/export")
	public ResponseEntity<InputStreamResource> exportColaborador(@PathVariable("matricula") String matricula) throws IOException {
		ColaboradorModel model = this.service.findByMatricula(matricula);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + model.getNome() + "_" + PLRUtils.today() + ".xlsx");
		
		return new ResponseEntity<>(new InputStreamResource(this.service.export(model)), headers, HttpStatus.OK);
	}
}
