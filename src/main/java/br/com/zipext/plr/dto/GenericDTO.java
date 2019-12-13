package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

public class GenericDTO {
	
	private Long id;
	private String matricula;
	private String nome;
	private String descricao;
	private Situacao situacao;
	
	public GenericDTO() {}
	
	public GenericDTO(Object model) {
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
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public static class Situacao {
		private String codigo;
		private String descricao;
		
		public Situacao() {}
		
		public Situacao(String codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}
		
		public String getCodigo() {
			return codigo;
		}
		
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	}

}
