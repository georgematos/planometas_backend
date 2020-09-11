package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;
import br.com.zipext.plr.repository.RelAvaliacaoProjetoRepository;

@Service
public class RelAvaliacaoProjetoService {

	@Autowired
	private RelAvaliacaoProjetoRepository repository;
	
	public List<RelAvaliacaoProjetoModel> findAll() {
		return
				this.repository.findAll();
	}
	
	@Transactional(readOnly = false)
	public RelAvaliacaoProjetoModel save(RelAvaliacaoProjetoModel model) {
		return
				this.repository.save(model);
	}
}
