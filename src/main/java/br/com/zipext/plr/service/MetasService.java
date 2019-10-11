package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.repository.MetasRepository;

@Service
public class MetasService {

	@Autowired
	private MetasRepository repository;
	
	@Transactional(readOnly = false)
	public MetasModel save(MetasModel model) {
		return this.repository.save(model);
	}
}
	