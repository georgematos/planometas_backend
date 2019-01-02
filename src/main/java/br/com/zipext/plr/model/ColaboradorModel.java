package br.com.zipext.plr.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CAD_COLABORADOR", schema = "BET_PLR")
public class ColaboradorModel {
	
	@Id
	@Column(name = "CD_MATRICULA")
	private String matricula;     
	
	@Column(name = "NM_COLABORADOR")
	private String nome;
	
	@Column(name = "IN_SITUACAO")
	private Character situacao;
	
	@Column(name = "DT_ADMISSAO")
	private LocalDate dataAdmissao;
	
	@Column(name = "DT_DESLIGAMENTO")
	private LocalDate dataDesligamento;
	
	@OneToMany(mappedBy = "pk.colaborador")
	private Set<ColaboradorCargoModel> colaboradoresCargos;
	
	@OneToMany(mappedBy = "pk.colaborador")
	private Set<ColaboradorMetaGeralModel> colaboradoresMetas;

	public ColaboradorModel() {}
	
	public ColaboradorModel(String matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDate getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(LocalDate dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
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
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		ColaboradorModel other = (ColaboradorModel) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
