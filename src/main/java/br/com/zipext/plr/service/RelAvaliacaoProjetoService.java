package br.com.zipext.plr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zipext.plr.dto.RelAvaliacaoProjetoDTO;
import br.com.zipext.plr.dto.RelMetaAvaliacaoProjetoDTO;
import br.com.zipext.plr.model.AvaliacaoProjetoOrcModel;
import br.com.zipext.plr.model.AvaliacaoProjetoPrazoModel;
import br.com.zipext.plr.model.AvaliacaoProjetoQualiModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel.RelAvaliacaoProjetoPK;
import br.com.zipext.plr.repository.RelAvaliacaoProjetoRepository;

@Service
public class RelAvaliacaoProjetoService {

	@Autowired
	private RelAvaliacaoProjetoRepository repository;
	
	public List<RelAvaliacaoProjetoModel> findAll() {
		return
				this.repository.findAll();
	}
	
	public RelAvaliacaoProjetoModel findById(RelAvaliacaoProjetoPK pk) {
		return repository.findById(pk).get();
	}
	
	@Transactional(readOnly = false)
	public RelAvaliacaoProjetoModel save(RelAvaliacaoProjetoModel model) {
		return
				this.repository.save(model);
	}
	
	public RelAvaliacaoProjetoPK getPKByDTO(RelMetaAvaliacaoProjetoDTO dto) {
		RelAvaliacaoProjetoPK pk = new RelAvaliacaoProjetoPK();
		
		AvaliacaoProjetoPrazoModel prazo = new AvaliacaoProjetoPrazoModel();
		prazo.setId(dto.getAvaliacaoProjeto().getAvaliacaoPrazo().getId());

		AvaliacaoProjetoQualiModel quali = new AvaliacaoProjetoQualiModel();
		quali.setId(dto.getAvaliacaoProjeto().getAvaliacaoQualidade().getId());

		AvaliacaoProjetoOrcModel orc = new AvaliacaoProjetoOrcModel();
		orc.setId(dto.getAvaliacaoProjeto().getAvaliacaoOrcamento().getId());
		
		pk.setAvaliacaoProjetoPrazo(prazo);
		pk.setAvaliacaoProjetoQuali(quali);
		pk.setAvaliacaoProjetoOrc(orc);
		
		return pk;
	}
	
	public RelAvaliacaoProjetoPK getPKByDTO(RelAvaliacaoProjetoDTO dto) {
		RelAvaliacaoProjetoPK pk = new RelAvaliacaoProjetoPK();
		
		AvaliacaoProjetoPrazoModel prazo = new AvaliacaoProjetoPrazoModel();
		prazo.setId(dto.getAvaliacaoPrazo().getId());

		AvaliacaoProjetoQualiModel quali = new AvaliacaoProjetoQualiModel();
		quali.setId(dto.getAvaliacaoQualidade().getId());

		AvaliacaoProjetoOrcModel orc = new AvaliacaoProjetoOrcModel();
		orc.setId(dto.getAvaliacaoOrcamento().getId());
		
		pk.setAvaliacaoProjetoPrazo(prazo);
		pk.setAvaliacaoProjetoQuali(quali);
		pk.setAvaliacaoProjetoOrc(orc);
		
		return pk;
	}
}
