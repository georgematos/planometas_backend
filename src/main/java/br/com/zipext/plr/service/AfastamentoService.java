package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.AfastamentoModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.repository.AfastamentoRepository;

@Service
public class AfastamentoService {

	@Autowired
	private AfastamentoRepository repository;
	
	@Transactional(readOnly = true)
	public AfastamentoModel findAfastamentoAtivoByColaborador(ColaboradorModel colaborador) {
		return 
				this.repository.findAfastamentoAtivoByColaborador(colaborador);
	}
	
	@Transactional(readOnly = false)
	public AfastamentoModel save(AfastamentoModel model) {
		return this.repository.save(model);
	}
}
