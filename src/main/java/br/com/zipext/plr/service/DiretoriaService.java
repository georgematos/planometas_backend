package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.DiretoriaModel;
import br.com.zipext.plr.repository.DiretoriaRepository;

@Service
public class DiretoriaService {

	@Autowired
	private DiretoriaRepository repository;
	
	@Transactional(readOnly = true)
	public List<DiretoriaModel> findAllByOrderByNomeAsc() {
		return this.repository.findAllByOrderByNomeAsc();
	}
}
