package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.MetaEspecificaMensalModel;
import br.com.zipext.plr.repository.MetaEspecificaMensalRepository;

@Service
public class MetaEspecificaMensalService {

	@Autowired
	private MetaEspecificaMensalRepository repository;
	
	@Transactional(readOnly = true)
	public List<MetaEspecificaMensalModel> findByFilter(Long idMeta, String matricula, Integer sequencia) {
		return
				this.repository.findByFilter(idMeta, matricula, sequencia);
	}
	
	@Transactional(readOnly = true)
	public List<MetaEspecificaMensalModel> findByMatricula(String matricula) {	
		return
				this.repository.findByMatricula(matricula);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void saveAll(List<MetaEspecificaMensalModel> models) {
		this.repository.saveAll(models);
	}
}
