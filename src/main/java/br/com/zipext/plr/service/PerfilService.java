package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.PerfilModel;
import br.com.zipext.plr.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository repository;
	
	@Transactional(readOnly = true)
	public List<PerfilModel> findBySituacaoOrderByNomeAsc(String situacao) {
		return this.repository.findBySituacaoOrderByNomeAsc(situacao);		
	}
}
