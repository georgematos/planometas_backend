package br.com.zipext.plr.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.MetasModel;

public class MetasDTO {
	
	public Long id;

	public String descricao;
	
	public String observacao;
	
	public String situacao;
	
	public String isQuantitativa;
	
	public BigDecimal valor;
	
	public FrequenciaMedicaoDTO frequenciaMedicao;
	
	public TipoMedicaoDTO tipoMedicao;
	
	public TipoMetaDTO tipoMeta;

	public MetasDTO() {}
	
	public MetasDTO(MetasModel model) {
		BeanUtils.copyProperties(model, this);
		if (model.getTipoMeta() != null) {
			this.tipoMeta = new TipoMetaDTO(model.getTipoMeta());
		}
		
		if (model.getTipoMedicao() != null) {
			this.tipoMedicao = new TipoMedicaoDTO(model.getTipoMedicao());
		}
		
		if (model.getFrequenciaMedicao() != null) {
			this.frequenciaMedicao = new FrequenciaMedicaoDTO(model.getFrequenciaMedicao());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getIsQuantitativa() {
		return isQuantitativa;
	}

	public void setIsQuantitativa(String isQuantitativa) {
		this.isQuantitativa = isQuantitativa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public FrequenciaMedicaoDTO getFrequenciaMedicao() {
		return frequenciaMedicao;
	}

	public void setFrequenciaMedicao(FrequenciaMedicaoDTO frequenciaMedicao) {
		this.frequenciaMedicao = frequenciaMedicao;
	}

	public TipoMedicaoDTO getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(TipoMedicaoDTO tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public TipoMetaDTO getTipoMeta() {
		return tipoMeta;
	}

	public void setTipoMeta(TipoMetaDTO tipoMeta) {
		this.tipoMeta = tipoMeta;
	}
}
