package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.AvaliacaoProjetoPrazoModel;
import br.com.zipext.plr.repository.AvaliacaoProjetoPrazoRepository;

@Service
public class AvaliacaoProjetoPrazoService {

	@Autowired
	private AvaliacaoProjetoPrazoRepository repository;
	
	@Transactional(readOnly = true)
	public List<AvaliacaoProjetoPrazoModel> findAllOrderedByDescricao() {
		return
				this.repository.findAllByOrderByDescricaoAsc();
	}
	
	@Transactional(readOnly = false)
	public AvaliacaoProjetoPrazoModel save(AvaliacaoProjetoPrazoModel model) {
		return
				this.repository.save(model);
	}
}
