package br.com.zipext.plr.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.UsuarioDTO;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UsuarioDTO dto) throws Exception {
		UsuarioModel usuario = this.service.processLogin(dto.getModel());
		if (usuario == null) {
			//return new ResponseEntity<>("Login inválido! ", HttpStatus.NOT_FOUND);
			throw new Exception("Usuário não encontrado!");
		} else {
			return new ResponseEntity<>(new UsuarioDTO(usuario), HttpStatus.OK);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@RequestBody UsuarioDTO dto) {
		return new ResponseEntity<>(new UsuarioDTO(this.service.update(dto.getModel())), HttpStatus.OK);
	}
}
