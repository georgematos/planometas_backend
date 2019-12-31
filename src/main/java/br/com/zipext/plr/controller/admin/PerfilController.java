package br.com.zipext.plr.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zipext.plr.dto.PerfilDTO;
import br.com.zipext.plr.dto.PerfilUsuarioDTO;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.PerfilPermissaoModel;
import br.com.zipext.plr.model.PerfilUsuarioModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.service.PerfilPermissaoService;
import br.com.zipext.plr.service.PerfilService;
import br.com.zipext.plr.service.PerfilUsuarioService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
	
	@Autowired
	private PerfilService service;
	
	@Autowired
	private PerfilPermissaoService perfilPermissaoService;
	
	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@GetMapping
	public ResponseEntity<List<PerfilDTO>> findAll() {
		return new ResponseEntity<>(
				this.service.findBySituacaoOrderByNomeAsc(EnumSituacao.ATIVO.getCodigo().toString())
							.stream().map(PerfilDTO::new).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("/permissao/{login}")
	public ResponseEntity<PerfilDTO> getPerfilPermissaoUsuario(@PathVariable("login") String login) {
		PerfilUsuarioModel perfilUsuarioModel = this.perfilUsuarioService.findByUsuario(new UsuarioModel(login));
		if (perfilUsuarioModel == null) {
			perfilUsuarioModel = this.perfilUsuarioService.associaUsuarioGenerico(login);
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
	
	@PostMapping("/bindperfilusuario")
	public ResponseEntity<PerfilUsuarioDTO> save(@RequestBody PerfilUsuarioDTO dto) {
		PerfilUsuarioModel model = dto.obterModel();
		this.perfilUsuarioService.deleteByUsuario(model.getPk().getUsuario());
		
		return new ResponseEntity<>(
				new PerfilUsuarioDTO(this.perfilUsuarioService.save(model)), HttpStatus.OK);
	}
}
