package br.com.zipext.plr.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zipext.plr.enums.EnumSituacao;

@Entity
@Table(schema = "CORPORATIVO" ,name = "CAD_COLABORADOR")
public class ColaboradorModel {

	@Id
	@Column(name = "CD_MATRICULA")
	private String matricula; 
	
	@Column(name = "NM_COLABORADOR")
	private String nome;
	
	@Column(name = "DT_ADMISSAO")
	private LocalDate dataAdmissao;
	
	@Column(name = "DT_DEMISSAO")
	private LocalDate dataDemissao;
	
	@Column(name = "FL_SIT_ELEGIVEL")
	private String elegivel;
	
	@Column(name = "FL_SIT_CADASTO")
	private EnumSituacao situacao;
	
	@ManyToOne
	@JoinColumn(name = "CD_DIRETORIA")
	private DiretoriaModel diretoria;
	
	@ManyToOne
	@JoinColumn(name = "CD_CARGO")
	private CargoModel cargo;
	
	@ManyToOne
	@JoinColumn(name = "CD_TIME")
	private TimeModel time;
	
	@ManyToOne
	@JoinColumn(name = "CD_FILIAL")
	private FilialModel filial;
	
	public ColaboradorModel() {}
	
	public ColaboradorModel(String matricula) {
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDate getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(LocalDate dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public String getElegivel() {
		return elegivel;
	}

	public void setElegivel(String elegivel) {
		this.elegivel = elegivel;
	}

	public EnumSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumSituacao situacao) {
		this.situacao = situacao;
	}

	public DiretoriaModel getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(DiretoriaModel diretoria) {
		this.diretoria = diretoria;
	}

	public CargoModel getCargo() {
		return cargo;
	}

	public void setCargo(CargoModel cargo) {
		this.cargo = cargo;
	}

	public TimeModel getTime() {
		return time;
	}

	public void setTime(TimeModel time) {
		this.time = time;
	}

	public FilialModel getFilial() {
		return filial;
	}

	public void setFilial(FilialModel filial) {
		this.filial = filial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}
}
