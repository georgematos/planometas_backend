package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void delete(ColaboradorMetaEspecificaModel model) {
		this.delete(model.getPk().getColaborador(), model.getPk().getMetaEspecifica(), model.getPk().getSequencia());
	}
	
	public void delete(ColaboradorModel colaborador, MetaEspecificaModel metaEspecifica, Integer sequencia) {
		this.repository.deleteColaboradorMetaByFilter(colaborador.getMatricula(), metaEspecifica.getId(), sequencia);
	}
	
	public void delete(ColaboradorModel colaborador, MetaEspecificaModel metaEspecifica) {
		this.repository.deleteColaboradorMeta(colaborador.getMatricula(), metaEspecifica.getId());
	}
	
	@Transactional(readOnly = true)
	public List<ColaboradorMetaEspecificaModel> findByMatricula(String matricula) {
		return
				this.repository.findByMatricula(matricula);
	}
	
	public ColaboradorMetaEspecificaModel save(ColaboradorModel colaboradorModel, MetaEspecificaModel metaEspecifica, Integer sequencia) {
		return
				this.save(new ColaboradorMetaEspecificaModel(colaboradorModel, metaEspecifica, sequencia));
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
	
	public ColaboradorMetaEspecificaModel update(ColaboradorMetaEspecificaModel model) {
		this.delete(model);
		return
				this.repository.save(model);
	}
	
}
