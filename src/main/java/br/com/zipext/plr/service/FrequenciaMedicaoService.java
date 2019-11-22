package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.repository.FrequenciaMedicaoRepository;

@Service
public class FrequenciaMedicaoService {

	@Autowired
	private FrequenciaMedicaoRepository repository;
	
	@Transactional(readOnly = true)
	public List<FrequenciaMedicaoModel> findAllByOrderByDescricaoAsc() {
		return
				this.repository.findAllByOrderByDescricaoAsc();
	}
}
