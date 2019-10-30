package br.com.zipext.plr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "CORPORATIVO", name = "CAD_TIME")
public class TimeModel {

	@Id
	@Column(name = "CD_TIME")
	private String codigo;
	
	@Column(name = "NM_TIME")
	private String nome;
	
	public TimeModel() {}
	
	public TimeModel(String codigo) {
		this.codigo = codigo;
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
