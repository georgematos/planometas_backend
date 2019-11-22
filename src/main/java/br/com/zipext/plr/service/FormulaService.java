package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.repository.FormulaRepository;

@Service
public class FormulaService {

	@Autowired
	private FormulaRepository repository;
	
	@Transactional(readOnly = true)
	public List<FormulaModel> findAllByOrderByNomeAsc() {
		return
				this.repository.findAllByOrderByNomeAsc();
	}
}
