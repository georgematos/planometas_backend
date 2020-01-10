package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.TemplateCampoModel;
import br.com.zipext.plr.repository.TemplateCampoRepository;

@Service
public class TemplateCampoService {

	@Autowired
	private TemplateCampoRepository repository;
	
	@Transactional(readOnly = true)
	public List<TemplateCampoModel> findAll() {
		return this.repository.findAll();
	}
	
	@Transactional(readOnly = false)
	public List<TemplateCampoModel> findByArea(String area) {
		return this.repository.findByArea(area);
	}
}
