package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.AvaliacaoProjetoOrcModel;
import br.com.zipext.plr.model.AvaliacaoProjetoPrazoModel;
import br.com.zipext.plr.model.AvaliacaoProjetoQualiModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel.RelAvaliacaoProjetoPK;
import br.com.zipext.plr.utils.PLRUtils;

public class RelAvaliacaoProjetoDTO {

	private BigDecimal valEscalonamento;
	private GenericDTO avaliacaoPrazo;
	private GenericDTO avaliacaoQualidade;
	private GenericDTO avaliacaoOrcamento;
	
	public RelAvaliacaoProjetoDTO() {}
	
	public RelAvaliacaoProjetoDTO(RelAvaliacaoProjetoModel model) {
		BeanUtils.copyProperties(model, this);
		this.avaliacaoPrazo = new GenericDTO(model.getPk().getAvaliacaoProjetoPrazo());
		this.avaliacaoQualidade = new GenericDTO(model.getPk().getAvaliacaoProjetoQuali());
		this.avaliacaoOrcamento = new GenericDTO(model.getPk().getAvaliacaoProjetoOrc());
	}
	
	public RelAvaliacaoProjetoModel obterModel() {
		RelAvaliacaoProjetoModel model = new RelAvaliacaoProjetoModel();

		AvaliacaoProjetoPrazoModel prazoModel = new AvaliacaoProjetoPrazoModel();
		AvaliacaoProjetoQualiModel qualiModel = new AvaliacaoProjetoQualiModel();
		AvaliacaoProjetoOrcModel orcModel = new AvaliacaoProjetoOrcModel();

		if (getAvaliacaoPrazo().getId() != null)
			prazoModel.setId(getAvaliacaoPrazo().getId());
		if (getAvaliacaoQualidade().getId() != null)
			qualiModel.setId(getAvaliacaoQualidade().getId());
		if (getAvaliacaoOrcamento().getId() != null)
			orcModel.setId(getAvaliacaoOrcamento().getId());

		RelAvaliacaoProjetoPK projPK = new RelAvaliacaoProjetoPK(prazoModel, qualiModel, orcModel);
		if (getValEscalonamento() != null)
			model.setValEscalonamento(getValEscalonamento());
		
		model.setPk(projPK);

		model.setResponsavelAlteracao(PLRUtils.SYS_USER);
		model.setAlteracao(LocalDateTime.now());
		
		return model;
	}

	public BigDecimal getValEscalonamento() {
		return valEscalonamento;
	}

	public void setValEscalonamento(BigDecimal valEscalonamento) {
		this.valEscalonamento = valEscalonamento;
	}

	public GenericDTO getAvaliacaoPrazo() {
		return avaliacaoPrazo;
	}

	public void setAvaliacaoPrazo(GenericDTO avaliacaoPrazo) {
		this.avaliacaoPrazo = avaliacaoPrazo;
	}

	public GenericDTO getAvaliacaoQualidade() {
		return avaliacaoQualidade;
	}

	public void setAvaliacaoQualidade(GenericDTO avaliacaoQualidade) {
		this.avaliacaoQualidade = avaliacaoQualidade;
	}

	public GenericDTO getAvaliacaoOrcamento() {
		return avaliacaoOrcamento;
	}

	public void setAvaliacaoOrcamento(GenericDTO avaliacaoOrcamento) {
		this.avaliacaoOrcamento = avaliacaoOrcamento;
	}
}
