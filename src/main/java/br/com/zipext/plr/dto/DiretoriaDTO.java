package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.DiretoriaModel;

public class DiretoriaDTO {
	
	private Long id;
	
	private String nome;

	public DiretoriaDTO() {}
	
	public DiretoriaDTO(DiretoriaModel model) {
		BeanUtils.copyProperties(model, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
