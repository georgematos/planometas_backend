package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.TempoModel;

public class TempoDTO {

	private Long id;
	private String descricao;
	private Integer ano;
	private Integer  mes;
	private Integer dia;
	
	public TempoDTO() {}
	
	public TempoDTO(TempoModel model) {
		BeanUtils.copyProperties(model, this);
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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}
}
