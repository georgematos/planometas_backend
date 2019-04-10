package br.com.zipext.plr.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CAD_META_GERAL", schema = "BET_PLR")
public class MetaGeralModel {
	
	@Id
	@Column(name = "CD_META")
	private Long id;
	
	@Column(name = "NM_META")
	private String nome;
	
	@Column(name = "DS_META")
	private String descricao;
	
	@Column(name = "IN_SITUACAO")
	private Character situacao;
	
	@Column(name = "NU_SEQUENCIA")
	private Integer sequencia;
	
	@OneToMany(mappedBy = "pk.metaGeral")
	private Set<ColaboradorMetaGeralModel> colaboradoresMetas;
	
	public MetaGeralModel() {}
	
	public MetaGeralModel(Long id) {
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

	public Set<ColaboradorMetaGeralModel> getColaboradoresMetas() {
		return colaboradoresMetas;
	}

	public void setColaboradoresMetas(Set<ColaboradorMetaGeralModel> colaboradoresMetas) {
		this.colaboradoresMetas = colaboradoresMetas;
	}
	
	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
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
		MetaGeralModel other = (MetaGeralModel) obj;
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
