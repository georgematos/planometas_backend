package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.HistoricoMetaEspecificaMensalModel;
import br.com.zipext.plr.repository.HistoricoMetaMensalRepository;

@Service
public class HistoricoMetaMensalService {

	@Autowired
	private HistoricoMetaMensalRepository repository;
	
	@Modifying
	@Transactional(readOnly = false)
	public void deleteByIdHistorico(Long idHistorico) {
		this.repository.deleteByIdHistorico(idHistorico);
	}
	
	@Transactional(readOnly = true)
	public List<HistoricoMetaEspecificaMensalModel> findByFilter(Long idMeta, String matricula, Integer sequencia, Long version) {
		return
				this.repository.findByFilter(idMeta, matricula, sequencia, version);
	}
}
