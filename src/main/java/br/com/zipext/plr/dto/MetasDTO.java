package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel.ColaboradorMetaEspecificaModelPK;
import br.com.zipext.plr.model.ColaboradorMetaGeralModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetaEspecificaModel;
import br.com.zipext.plr.utils.PLRUtils;

public class MetasDTO {
	
	private Long id;
	private Integer sequencia;
	private String frequenciaMedicao;
	private String tipoMeta;
	private String meta;
	private String prazo;
	private String observacao;
	private String descricao;
	private String possuiMetaGeral;
	private BigDecimal bonus;
	private BigDecimal valor;
	private BigDecimal peso;
	private BigDecimal valMeta;
	private String dataInclusao;
	private String responsavel;
	
	public MetasDTO() {}
	
	public MetasDTO(ColaboradorMetaGeralModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			this.id = model.getPk().getMetaGeral().getId();
			this.meta = model.getPk().getMetaGeral().getNome();
			this.observacao = model.getObservacao();
		}
	}
	
	public MetasDTO(ColaboradorMetaEspecificaModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			this.id = model.getPk().getMetaEspecifica().getId();
 			this.prazo = model.getPrazo() != null ? 
					model.getPrazo().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS)) : "";
			this.dataInclusao = model.getDataInclusao() != null ?
					model.getDataInclusao().toLocalDate().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_DB)) : "";
			this.responsavel = model.getResponsavel();
			this.observacao = model.getObservacao();
			this.sequencia = model.getPk().getSequencia();
		}
	}
	
	@JsonIgnore
	public ColaboradorMetaEspecificaModel getMetasForColaborador(String matricula) {
		ColaboradorMetaEspecificaModel model = new ColaboradorMetaEspecificaModel();
		BeanUtils.copyProperties(this, model);

		model.setPk(new ColaboradorMetaEspecificaModelPK(new ColaboradorModel(matricula), new MetaEspecificaModel(this.id), this.sequencia));
		try {
			model.setPrazo(LocalDate.parse(this.prazo.substring(0, 10), DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS)));
			model.setDataInclusao(LocalDateTime.now());
		} catch (Exception e1) {
			model.setPrazo(LocalDate.now());
		}

		return model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFrequenciaMedicao() {
		return frequenciaMedicao;
	}

	public void setFrequenciaMedicao(String frequenciaMedicao) {
		this.frequenciaMedicao = frequenciaMedicao;
	}

	public String getPossuiMetaGeral() {
		return possuiMetaGeral;
	}

	public void setPossuiMetaGeral(String possuiMetaGeral) {
		this.possuiMetaGeral = possuiMetaGeral;
	}

	public String getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTipoMeta() {
		return tipoMeta;
	}

	public void setTipoMeta(String tipoMeta) {
		this.tipoMeta = tipoMeta;
	}

	public BigDecimal getValMeta() {
		return valMeta;
	}

	public void setValMeta(BigDecimal valMeta) {
		this.valMeta = valMeta;
	}
}
