package br.com.zipext.plr.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	@Column(name = "DS_BASE64_IMG")
	private String base64Img;
	
	@Column(name = "IN_DIRETORIA")
	private Character isDiretoria;
	
	@Column(name = "DS_UNIDADE")
	private String unidade;
	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL)
	private Set<UsuarioModel> usuarios;
	
	@OneToMany(mappedBy = "colaborador")
	private Set<HistoricoModel> historico;
	
	@OneToMany(mappedBy = "pk.colaborador")
	private Set<ColaboradorCargoModel> colaboradoresCargos;
	
	@OneToMany(mappedBy = "pk.colaborador")
	private Set<ColaboradorMetaGeralModel> colaboradoresMetasGerais;

	@OneToMany(mappedBy = "pk.colaborador")
	private Set<ColaboradorMetaEspecificaModel> colaboradoresMetasEspecificas;
	
	@Transient
	private HistoricoModel historicoExport;
	
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
	
	public Set<ColaboradorMetaGeralModel> getColaboradoresMetasGerais() {
		return colaboradoresMetasGerais;
	}

	public void setColaboradoresMetasGerais(Set<ColaboradorMetaGeralModel> colaboradoresMetasGerais) {
		this.colaboradoresMetasGerais = colaboradoresMetasGerais;
	}

	public Set<ColaboradorMetaEspecificaModel> getColaboradoresMetasEspecificas() {
		return colaboradoresMetasEspecificas;
	}

	public void setColaboradoresMetasEspecificas(Set<ColaboradorMetaEspecificaModel> colaboradoresMetasEspecificas) {
		this.colaboradoresMetasEspecificas = colaboradoresMetasEspecificas;
	}

	public Set<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Set<HistoricoModel> getHistorico() {
		return historico;
	}

	public void setHistorico(Set<HistoricoModel> historico) {
		this.historico = historico;
	}
	
	public HistoricoModel getHistoricoExport() {
		return historicoExport;
	}

	public void setHistoricoExport(HistoricoModel historicoExport) {
		this.historicoExport = historicoExport;
	}
	
	public String getBase64Img() {
		return base64Img;
	}

	public void setBase64Img(String base64Img) {
		this.base64Img = base64Img;
	}

	public Character getIsDiretoria() {
		return isDiretoria;
	}

	public void setIsDiretoria(Character isDiretoria) {
		this.isDiretoria = isDiretoria;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
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
