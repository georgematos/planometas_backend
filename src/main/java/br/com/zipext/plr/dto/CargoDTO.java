package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.dto.GenericDTO.Situacao;
import br.com.zipext.plr.model.CargoModel;

public class CargoDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private Situacao situacao;
	private DiretoriaDTO diretoria;
	
	public CargoDTO() {}
	
	public CargoDTO(CargoModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			this.diretoria = new DiretoriaDTO(model.getDiretoria());
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

	public DiretoriaDTO getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(DiretoriaDTO diretoria) {
		this.diretoria = diretoria;
	}
}
