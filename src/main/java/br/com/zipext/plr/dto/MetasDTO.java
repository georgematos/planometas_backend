package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.utils.PLRUtils;

public class MetasDTO {
	
	public Long id;
	
	private boolean isNewMeta;

	public String descricao;
	
	public String observacao;
	
	public String situacao;
	
	public String prazo;
	
	public String isQuantitativa;
	
	public BigDecimal valor;
	
	public FrequenciaMedicaoDTO frequenciaMedicao;
	
	public TipoMedicaoDTO tipoMedicao;
	
	public TipoMetaDTO tipoMeta;
	
	public FormulaDTO formula;

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
		
		if (model.getFormula() != null) {
			this.formula = new FormulaDTO(model.getFormula());
		}
		
		this.prazo = model.getPrazo().getDescricao();
	}
	
	public MetasModel obterModel() {
		MetasModel model  = new MetasModel();

		BeanUtils.copyProperties(this, model);
		if (this.formula != null) {
			model.setFormula(new FormulaModel(this.formula.getId()));
		}
		
		if (this.frequenciaMedicao != null) {
			model.setFrequenciaMedicao(new FrequenciaMedicaoModel(this.frequenciaMedicao.getId()));
		}
		
		if (this.tipoMeta!= null) {
			model.setTipoMeta(new TipoMetaModel(this.tipoMeta.getId()));
		}
		
		if (this.tipoMedicao != null) {
			model.setTipoMedicao(new TipoMedicaoModel(this.tipoMedicao.getId()));
		}
		
		if (StringUtils.isNotBlank(this.prazo)) {
			model.setPrazo(new TempoModel(PLRUtils.getSkyTempoFromStringDate(this.prazo)));
		}
		
		model.setInclusao(LocalDateTime.now());
		model.setResponsavelInclusao("SISTEMA");
		
		return
				model;
		
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

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public FormulaDTO getFormula() {
		return formula;
	}

	public void setFormula(FormulaDTO formula) {
		this.formula = formula;
	}

	public boolean isNewMeta() {
		return isNewMeta;
	}

	public void setNewMeta(boolean isNewMeta) {
		this.isNewMeta = isNewMeta;
	}
}
