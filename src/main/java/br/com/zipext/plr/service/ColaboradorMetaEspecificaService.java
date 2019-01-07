package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetaEspecificaModel;
import br.com.zipext.plr.repository.ColaboradorMetaEspecificaRepository;

@Service
public class ColaboradorMetaEspecificaService {
	
	@Autowired
	private ColaboradorMetaEspecificaRepository repository;
	
	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void delete(ColaboradorModel colaborador, MetaEspecificaModel metaEspecifica) {
		this.repository.deleteColaboradorMeta(colaborador, metaEspecifica);
	}

	
	@Transactional(readOnly = true)
	public List<ColaboradorMetaEspecificaModel> findByMatricula(String matricula) {
		return
				this.repository.findByMatricula(matricula);
	}
	
	public ColaboradorMetaEspecificaModel save(ColaboradorModel colaboradorModel, MetaEspecificaModel metaEspecifica) {
		return
				this.save(new ColaboradorMetaEspecificaModel(colaboradorModel, metaEspecifica));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ColaboradorMetaEspecificaModel save(ColaboradorMetaEspecificaModel model) {
		return
				this.repository.save(model);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ColaboradorMetaEspecificaModel> saveAll(List<ColaboradorMetaEspecificaModel> models) {
		return
				this.repository.saveAll(models);
	}
	
	public List<ColaboradorMetaEspecificaModel> updateAll(List<ColaboradorMetaEspecificaModel> models) {
		if (models != null && !models.isEmpty()) {
			this.repository.delete(models.get(0));
		}
		
		return
				this.saveAll(models);
	}
}
