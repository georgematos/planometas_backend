package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.repository.HistoricoMetaEspecificaRepository;

@Service
public class HistoricoMetaEspecificaService {
	
	@Autowired
	private HistoricoMetaEspecificaRepository repository;
	
	@Modifying
	@Transactional(readOnly = false)
	public void deleteByIdHistorico(Long idHistorico) {
		this.repository.deleteByIdHistorico(idHistorico);
	}
}
