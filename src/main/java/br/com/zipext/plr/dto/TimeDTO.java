package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.TimeModel;

public class TimeDTO {
	
	private String codigo;
	private String nome;
	
	public TimeDTO() {}
	
	public TimeDTO(TimeModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
