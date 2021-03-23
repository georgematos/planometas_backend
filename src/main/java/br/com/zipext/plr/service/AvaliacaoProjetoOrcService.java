package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.AvaliacaoProjetoOrcModel;
import br.com.zipext.plr.repository.AvaliacaoProjetoOrcRepository;

@Service
public class AvaliacaoProjetoOrcService {

	@Autowired
	private AvaliacaoProjetoOrcRepository repository;
	
	@Transactional(readOnly = true)
	public List<AvaliacaoProjetoOrcModel> findAllOrderedByDescricao() {
		return
				this.repository.findAllByOrderByDescricaoAsc();
	}
	
	@Transactional(readOnly = false)
	public AvaliacaoProjetoOrcModel save(AvaliacaoProjetoOrcModel model) {
		return
				this.repository.save(model);
	}
}
