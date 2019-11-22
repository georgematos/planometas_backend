package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.repository.MetasRepository;

@Service
public class MetasService {

	@Autowired
	private MetasRepository repository;
	
	@Transactional(readOnly = true)
	public MetasModel findByDescricaoAndSituacao(String descricao, String situacao) {
		return
				this.repository.findByDescricaoAndSituacao(descricao, situacao);
	}
	
	@Transactional(readOnly = true)
	public List<MetasModel> findByFilter(String meta, String situacao, String tipoMedicao, String tipoMeta, String formula, String frequenciaMedicao) {
		return 
				this.repository.findByFilter(meta, situacao, tipoMedicao, tipoMeta, formula, frequenciaMedicao);
	}
	
	@Transactional(readOnly = false)
	public MetasModel save(MetasModel model) {
		return this.repository.save(model);
	}
}
	