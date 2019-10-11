package br.com.zipext.plr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "CORPORATIVO", name = "CAD_CARGO")
public class CargoModel {

	@Id
	@Column(name = "CD_CARGO")
	public Long id;
	
	@Column(name = "NM_CARGO")
	public String nome;
	
	@ManyToOne
	@JoinColumn(name = "CD_EQUIVALENCIA")
	public EquivalenciaModel equivalencia;

	public CargoModel() {}
	
	public CargoModel(Long id) {
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

	public EquivalenciaModel getEquivalencia() {
		return equivalencia;
	}

	public void setEquivalencia(EquivalenciaModel equivalencia) {
		this.equivalencia = equivalencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CargoModel other = (CargoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
