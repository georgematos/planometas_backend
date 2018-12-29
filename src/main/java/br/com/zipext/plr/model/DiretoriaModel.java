package br.com.zipext.plr.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.zipext.plr.enums.EnumSituacao;

@Entity
@Table(name = "CAD_DIRETORIA")
public class DiretoriaModel {

	@Id
	@Column(name = "CD_DIRETORIA")
	private Long id;
	
	@Column(name = "NM_DIRETORIA")
	private String nome;
	
	@Column(name = "DS_DIRETORIA")
	private String descricao;
	
	@Column(name = "IN_SITUACAO")
	private EnumSituacao situacao;
	
	@OneToMany(mappedBy = "diretoria", cascade = CascadeType.ALL)
	private Set<CargoModel> cargos;
	
	public DiretoriaModel() {}
	
	public DiretoriaModel(Long id) {
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

	public EnumSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumSituacao situacao) {
		this.situacao = situacao;
	}
	
	public Set<CargoModel> getCargos() {
		return cargos;
	}

	public void setCargos(Set<CargoModel> cargos) {
		this.cargos = cargos;
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
		DiretoriaModel other = (DiretoriaModel) obj;
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
