package br.com.zipext.plr.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.EquivalenciaModel;

public class EquivalenciaDTO {
	
	private Long id;
	private String descricao;
	private BigDecimal multiplicador;
	private BigDecimal limiteMultiplicador;
	
	public EquivalenciaDTO() {}
	
	public EquivalenciaDTO(EquivalenciaModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
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

	public BigDecimal getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(BigDecimal multiplicador) {
		this.multiplicador = multiplicador;
	}

	public BigDecimal getLimiteMultiplicador() {
		return limiteMultiplicador;
	}

	public void setLimiteMultiplicador(BigDecimal limiteMultiplicador) {
		this.limiteMultiplicador = limiteMultiplicador;
	}
}
