package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.enums.EnumPerfil;
import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.PerfilUsuarioModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.repository.PerfilUsuarioRepository;

@Service
public class PerfilUsuarioService {

	@Autowired
	private PerfilUsuarioRepository repository;
	
	public PerfilUsuarioModel associaUsuarioGenerico(String login) {
		PerfilUsuarioModel model = new PerfilUsuarioModel(EnumPerfil.buildPerfilGenerico(), new UsuarioModel(login));
		model.setSituacao(EnumSituacao.ATIVO.getCodigo().toString());
		return 
				this.save(model);
	}
	
	@Modifying
	@Transactional(readOnly = false)
	public void deleteByUsuario(UsuarioModel usuario) {
		this.repository.deleteByUsuario(usuario);
	}
	
	@Transactional(readOnly = true)
	public PerfilUsuarioModel findByUsuario(UsuarioModel usuario) {
		return
				this.repository.findByUsuarioAndSituacao(usuario, EnumSituacao.ATIVO.getCodigo().toString());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public PerfilUsuarioModel save(PerfilUsuarioModel model) {
		return
				this.repository.save(model);
	}
}
