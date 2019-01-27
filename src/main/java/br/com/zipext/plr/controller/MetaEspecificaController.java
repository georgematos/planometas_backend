package br.com.zipext.plr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.MetasDTO;
import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.service.ColaboradorMetaEspecificaService;

@RestController
@RequestMapping("/metaEspecifica")
public class MetaEspecificaController {

	@Autowired
	private ColaboradorMetaEspecificaService service;
	
	@PostMapping("/colaborador/{matricula}")
	public ResponseEntity<Void> save(@RequestBody MetasDTO dto, @PathVariable("matricula") String matricula) {
		ColaboradorMetaEspecificaModel model = dto.getMetasForColaborador(matricula);
		
		this.service.save(model);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/colaborador/{matricula}")
	public ResponseEntity<Void> delete(@RequestBody MetasDTO dto, @PathVariable("matricula") String matricula) {
		ColaboradorMetaEspecificaModel model = dto.getMetasForColaborador(matricula);
		this.service.delete(model);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/colaborador/{matricula}")
	public ResponseEntity<Void> update(@RequestBody MetasDTO dto, @PathVariable("matricula") String matricula) {
		ColaboradorMetaEspecificaModel model = dto.getMetasForColaborador(matricula);
		this.service.update(model);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
