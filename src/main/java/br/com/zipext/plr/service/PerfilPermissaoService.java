package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.PerfilModel;
import br.com.zipext.plr.model.PerfilPermissaoModel;
import br.com.zipext.plr.repository.PerfilPermissaoRepository;

@Service
public class PerfilPermissaoService {

	@Autowired
	private PerfilPermissaoRepository repository;
	
	@Transactional(readOnly = true)
	public PerfilPermissaoModel findByPerfil(PerfilModel perfil) {
		return 
				this.repository.findByPerfil(perfil);
	}
}
