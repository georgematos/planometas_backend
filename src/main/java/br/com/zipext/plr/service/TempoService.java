package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.repository.TempoRepository;

@Service
public class TempoService {

	@Autowired
	private TempoRepository repository;

	public List<Integer> findDistinctAno() {
		return
				this.repository.findDistinctAno();
	}
	
	@Transactional(readOnly = false)
	public TempoModel save(TempoModel model) {
		return this.repository.save(model);
	}
}
