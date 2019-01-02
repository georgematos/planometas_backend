package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.MetaGeralModel;
import br.com.zipext.plr.repository.MetaGeralRepository;

@Service
public class MetaGeralService {

	@Autowired
	private MetaGeralRepository repository;
	
	@Transactional(readOnly = true)
	public List<MetaGeralModel> findAll() {
		return this.repository.findAll();
	}
}
