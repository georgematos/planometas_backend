package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FormulaModel;

public class FormulaDTO {
	
	private Long id;
	
	private String nome;
	
	public FormulaDTO() {}
	
	public FormulaDTO(FormulaModel model)	{
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
