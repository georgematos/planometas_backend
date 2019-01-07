package br.com.zipext.plr.dto;

import java.math.BigDecimal;
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
	private String meta;
	private String prazo;
	private String observacao;
	private String descricao;
	private BigDecimal bonus;
	private BigDecimal valor;
	private BigDecimal peso;
	
	public MetasDTO() {}
	
	public MetasDTO(ColaboradorMetaGeralModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			this.prazo = model.getPrazo() != null ? 
					model.getPrazo().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN)) : "";
			this.id = model.getPk().getMetaGeral().getId();
			this.meta = model.getPk().getMetaGeral().getNome();
			this.observacao = model.getObservacao();
		}
	}
	
	public MetasDTO(ColaboradorMetaEspecificaModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
 			this.prazo = model.getPrazo() != null ? 
					model.getPrazo().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN)) : "";
			this.id = model.getPk().getMetaEspecifica().getId();
			this.observacao = model.getObservacao();
		}
	}
	
	@JsonIgnore
	public ColaboradorMetaEspecificaModel getMetasForColaborador(String matricula) {
		ColaboradorMetaEspecificaModel model = new ColaboradorMetaEspecificaModel();
		BeanUtils.copyProperties(this, model);
		
		model.setPk(new ColaboradorMetaEspecificaModelPK(new ColaboradorModel(matricula), new MetaEspecificaModel(this.id)));
		//model.setPrazo(ZonedDateTime.parse(this.prazo, DateTimeFormatter.ofPattern(PLRUtils.FRONT_DATE_PATTERN)).toLocalDateTime()); ;
		model.setPrazo(LocalDateTime.now());
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
}
