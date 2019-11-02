package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository repository;
	
	@Transactional(readOnly = true)
	public ColaboradorModel findByMatricula(String matricula) {
		return
				this.repository.findByMatricula(matricula);
	}
	
	@Transactional(readOnly = false)
	public ColaboradorModel save(ColaboradorModel model) {
		return this.repository.save(model);
	}
}
