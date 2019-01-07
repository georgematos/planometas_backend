package br.com.zipext.plr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.MetasDTO;
import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetaEspecificaModel;
import br.com.zipext.plr.service.ColaboradorMetaEspecificaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/metaEspecifica")
public class MetaEspecificaController {

	@Autowired
	private ColaboradorMetaEspecificaService service;
	
	@PostMapping("/colaborador/{matricula}")
	public ResponseEntity<Void> save(@RequestBody List<MetasDTO> dtos, @PathVariable("matricula") String matricula) {
		List<ColaboradorMetaEspecificaModel> models = new ArrayList<>();
		dtos.forEach(dto -> models.add(dto.getMetasForColaborador(matricula)));
		
		this.service.updateAll(models);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/colaborador/{matricula}/meta/{idMeta}")
	public ResponseEntity<Void> delete(@PathVariable("matricula") String matricula, @PathVariable("idMeta") Long idMeta) {
		this.service.delete(new ColaboradorModel(matricula), new MetaEspecificaModel(idMeta));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
