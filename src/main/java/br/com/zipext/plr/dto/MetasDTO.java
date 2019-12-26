package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.MetasPeriodoModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.model.TipoMetaModel;
import br.com.zipext.plr.utils.PLRUtils;

public class MetasDTO {
	
	private Long id;
	
	private boolean isAtivaForPeriodo;
	
	private boolean isNewMeta;

	private String descricao;
	
	private String observacao;
	
	private String situacao;
	
	private String prazo;
	
	private String isQuantitativa;
	
	private BigDecimal valor;
	
	private FrequenciaMedicaoDTO frequenciaMedicao;
	
	private TipoMedicaoDTO tipoMedicao;
	
	private TipoMetaDTO tipoMeta;
	
	private FormulaDTO formula;
	
	private GenericDTO metaNumerador;
	
	private GenericDTO metaDenominador;
	
	private GenericDTO aprovador; 

	public MetasDTO() {}
	
	public MetasDTO(MetasModel model, Integer periodoPLR) {
		this(model);
		List<MetasPeriodoModel> metasPeriodo = model.getMetasPeriodo();
		if (metasPeriodo != null && !metasPeriodo.isEmpty()) {
			isAtivaForPeriodo = metasPeriodo.stream().filter(mp -> mp.getPk().getTempo().getAno().equals(periodoPLR)).findFirst().isPresent();
		}
	}
	
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
		
		if (model.getPrazo() != null) {
			this.prazo = model.getPrazo().getDescricao();
		}
		
		if (model.getMetaNumerador() != null) {
			this.metaNumerador = new GenericDTO(model.getMetaNumerador());
		}
		
		if (model.getMetaDenominador() != null) {
			this.metaDenominador = new GenericDTO(model.getMetaDenominador());
		}
		
		if (model.getAprovador() != null) {
			this.aprovador = new GenericDTO(model.getAprovador());
		}
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
		
		if (this.metaNumerador != null) {
			model.setMetaNumerador(new MetasModel(this.metaNumerador.getId()));
		}
		
		if (this.metaDenominador != null) {
			model.setMetaDenominador(new MetasModel(this.metaDenominador.getId()));
		}
		
		if (this.aprovador != null) {
			model.setAprovador(new ColaboradorModel(this.aprovador.getMatricula()));
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

	public GenericDTO getMetaNumerador() {
		return metaNumerador;
	}

	public void setMetaNumerador(GenericDTO metaNumerador) {
		this.metaNumerador = metaNumerador;
	}

	public GenericDTO getMetaDenominador() {
		return metaDenominador;
	}

	public void setMetaDenominador(GenericDTO metaDenominador) {
		this.metaDenominador = metaDenominador;
	}

	public GenericDTO getAprovador() {
		return aprovador;
	}

	public void setAprovador(GenericDTO aprovador) {
		this.aprovador = aprovador;
	}

	public boolean isAtivaForPeriodo() {
		return isAtivaForPeriodo;
	}

	public void setAtivaForPeriodo(boolean isAtivaForPeriodo) {
		this.isAtivaForPeriodo = isAtivaForPeriodo;
	}
}
