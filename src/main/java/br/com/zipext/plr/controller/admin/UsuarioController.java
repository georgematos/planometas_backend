package br.com.zipext.plr.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.UsuarioDTO;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.service.MetasPeriodoService;
import br.com.zipext.plr.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private MetasPeriodoService metasPeriodoService;
	
	@PostMapping("/login/{periodoPLR}")
	public ResponseEntity<Object> login(
			@RequestBody UsuarioDTO dto, 
			@PathVariable("periodoPLR") Integer periodoPLR) throws Exception {
		UsuarioModel usuario = this.service.processLogin(dto.getModel());
		if (usuario == null) {
			throw new Exception("Usuário não encontrado!");
		} 
		
		if (this.metasPeriodoService.countMetasByPeriodo(periodoPLR) == 0) {
			throw new Exception("O período informado não está aberto para consultas/cadastros. ");
		}
		
		return new ResponseEntity<>(new UsuarioDTO(usuario), HttpStatus.OK);
		
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@RequestBody UsuarioDTO dto) {
		return new ResponseEntity<>(new UsuarioDTO(this.service.update(dto.getModel())), HttpStatus.OK);
	}
}
