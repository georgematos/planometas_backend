package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.ColaboradorMetaGeralModel;
import br.com.zipext.plr.repository.ColaboradorMetasGeraisRepository;

@Service
public class ColaboradorMetasGeraisService {

	@Autowired
	private ColaboradorMetasGeraisRepository repository;
	
	@Transactional(readOnly = true)
	public List<ColaboradorMetaGeralModel> findAll() {
		return
				this.repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public ColaboradorMetaGeralModel findByMatricula(String matricula) {
		return
				this.repository.findByMatricula(matricula);
	}
}
