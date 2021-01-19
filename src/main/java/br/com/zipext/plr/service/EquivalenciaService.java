package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.EquivalenciaModel;
import br.com.zipext.plr.repository.EquivalenciaRepository;

@Service
public class EquivalenciaService {

	@Autowired
	private EquivalenciaRepository repository;

	@Transactional(readOnly = true)
	public List<EquivalenciaModel> findAllByOrderByDescricaoAsc() {
		return this.repository.findAllByOrderByDescricaoAsc();
	}

//	@Transactional(readOnly = true)
//	public List<EquivalenciaModel> findByFilter(Long codigo, String nome, Long equivalencia) {
//		return this.repository.findByFilter(codigo, nome, equivalencia);
//	}
}
