package br.com.zipext.plr.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CAD_CARGO", schema = "BET_PLR")
public class CargoModel {
	
	@Id
	@Column(name = "CD_CARGO")
	private Long id;
	
	@Column(name = "NM_CARGO")
	private String nome;
	
	@Column(name = "DS_CARGO")
	private String descricao;
	
	@Column(name = "IN_SITUACAO")
	private Character situacao;
	
	@ManyToOne
	@JoinColumn(name = "CD_DIRETORIA")
	private DiretoriaModel diretoria;
	
	@OneToMany(mappedBy = "pk.cargo")
	private Set<ColaboradorCargoModel> colaboradoresCargos;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public DiretoriaModel getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(DiretoriaModel diretoria) {
		this.diretoria = diretoria;
	}
	
	public Set<ColaboradorCargoModel> getColaboradoresCargos() {
		return colaboradoresCargos;
	}

	public void setColaboradoresCargos(Set<ColaboradorCargoModel> colaboradoresCargos) {
		this.colaboradoresCargos = colaboradoresCargos;
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
