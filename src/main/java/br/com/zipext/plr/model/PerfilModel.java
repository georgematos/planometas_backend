package br.com.zipext.plr.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "BET_PLR", name = "CAD_PERFIL")
public class PerfilModel {

	@Id
	@Column(name = "CD_PERFIL")
	private Long id;
	
	@Column(name = "NM_PERFIL")
	private String nome;
	
	@Column(name = "IN_SITUACAO")
	private Character situacao;
	
	@Column(name = "DT_INC")
	private LocalDateTime dataInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_LOGIN_INC")
	private UsuarioModel responsavel;

	public PerfilModel() {}
	
	public PerfilModel(Long id) {
		this.id = id;
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
	
	/*
	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}*/

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public UsuarioModel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(UsuarioModel responsavel) {
		this.responsavel = responsavel;
	}
}
