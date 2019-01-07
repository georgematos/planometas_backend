package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.zipext.plr.dto.GenericDTO.Situacao;
import br.com.zipext.plr.model.DiretoriaModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiretoriaDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private Situacao situacao;
	
	public DiretoriaDTO() {}
	
	public DiretoriaDTO(DiretoriaModel model) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}
