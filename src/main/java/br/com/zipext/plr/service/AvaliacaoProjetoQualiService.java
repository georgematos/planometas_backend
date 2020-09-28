package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.AvaliacaoProjetoQualiModel;
import br.com.zipext.plr.repository.AvaliacaoProjetoQualiRepository;

@Service
public class AvaliacaoProjetoQualiService {

	@Autowired
	private AvaliacaoProjetoQualiRepository repository;
	
	@Transactional(readOnly = true)
	public List<AvaliacaoProjetoQualiModel> findAllOrderedByDescricao() {
		return
				this.repository.findAllByOrderByDescricaoAsc();
	}
	
	@Transactional(readOnly = false)
	public AvaliacaoProjetoQualiModel save(AvaliacaoProjetoQualiModel model) {
		return
				this.repository.save(model);
	}
}
