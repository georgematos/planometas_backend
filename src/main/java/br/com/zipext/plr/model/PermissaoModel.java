package br.com.zipext.plr.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "AUTH", name = "CAD_PERMISSAO")
public class PermissaoModel {
	
	@Id
	@Column(name = "CD_PERMISSAO")
	private Long id;

	@Column(name = "DS_PERMISSAO")
	private String descricao;
	
	@Column(name = "IN_PERMISSAO")
	private Character permissao;
	
	@Column(name = "DT_INC")
	private LocalDateTime dataInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_LOGIN_INC")
	private UsuarioModel responsavel;
	
	public PermissaoModel() {}
	
	public PermissaoModel(Long id) {
		this.id = id;
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

	public Character getPermissao() {
		return permissao;
	}

	public void setPermissao(Character permissao) {
		this.permissao = permissao;
	}
}
