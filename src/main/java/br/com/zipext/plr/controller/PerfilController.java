package br.com.zipext.plr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.PerfilDTO;
import br.com.zipext.plr.enums.EnumPerfil;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.PerfilPermissaoModel;
import br.com.zipext.plr.model.PerfilUsuarioModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.service.PerfilPermissaoService;
import br.com.zipext.plr.service.PerfilUsuarioService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
	
	@Autowired
	private PerfilPermissaoService perfilPermissaoService;
	
	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@GetMapping("/permissao/{login}")
	public ResponseEntity<PerfilDTO> getPerfilPermissaoUsuario(@PathVariable("login") String login) {
		PerfilUsuarioModel perfilUsuarioModel = this.perfilUsuarioService.findByUsuario(new UsuarioModel(login));
		if (perfilUsuarioModel == null) {
			PerfilUsuarioModel perfilUsuario = new PerfilUsuarioModel(EnumPerfil.buildPerfilGenerico(), new UsuarioModel(login));
			perfilUsuario.setSituacao(EnumSituacao.ATIVO.getCodigo());
			perfilUsuarioModel = this.perfilUsuarioService.save(perfilUsuario);
		}
		
		PerfilPermissaoModel perfilPermissaoModel = this.perfilPermissaoService.findByPerfil(perfilUsuarioModel.getPk().getPerfil());
		PerfilDTO dto;
		if (perfilPermissaoModel != null) {
			dto = new PerfilDTO(perfilPermissaoModel);
		} else {
			dto = new PerfilDTO();
		}
		
		return new ResponseEntity<PerfilDTO>(dto, HttpStatus.OK);
	}
}
