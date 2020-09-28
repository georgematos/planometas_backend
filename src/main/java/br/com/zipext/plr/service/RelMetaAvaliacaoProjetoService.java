package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.RelMetaAvaliacaoProjetoModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.repository.RelMetaAvaliacaoProjetoRepository;

@Service
public class RelMetaAvaliacaoProjetoService {

	@Autowired
	private RelMetaAvaliacaoProjetoRepository repository;
	
	@Transactional(readOnly = false)
	public void deleteByMetaAndPeriodo(MetasModel meta, Integer anoPeriodoPLR) {
		this.repository.deleteByMetaAndPeriodo(meta);
	}
	
	@Transactional(readOnly = true)
	public List<RelMetaAvaliacaoProjetoModel> findByResponsavelAndVigencia(ColaboradorModel responsavel, TempoModel dataAvaliacao) {
		return 
				this.repository.findByResponsavelAndDataAvaliacao(responsavel, dataAvaliacao);
	}
	
	@Transactional(readOnly = false)
	public RelMetaAvaliacaoProjetoModel save(RelMetaAvaliacaoProjetoModel model) {
		return
				this.repository.save(model);
	}
}
