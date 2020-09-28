package br.com.zipext.plr.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;

public class RelAvaliacaoProjetoDTO {

	private BigDecimal valEscalonamento;
	private GenericDTO avaliacaoPrazo;
	private GenericDTO avaliacaoQualidade;
	
	public RelAvaliacaoProjetoDTO() {}
	
	public RelAvaliacaoProjetoDTO(RelAvaliacaoProjetoModel model) {
		BeanUtils.copyProperties(model, this);
		this.avaliacaoPrazo = new GenericDTO(model.getPk().getAvaliacaoProjetoPrazo());
		this.avaliacaoQualidade = new GenericDTO(model.getPk().getAvaliacaoProjetoQuali());
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
}
