package br.com.zipext.plr.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.zipext.plr.converter.LocalDateTimeConverter;

@Entity
@Table(schema = "METAS", name = "CAD_FORMULA")
public class FormulaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadFormulaSeq")
	@SequenceGenerator(schema = "METAS", name = "cadFormulaSeq", sequenceName = "cad_formula_seq", allocationSize = 1)
	@Column(name = "CD_FORMULA")
	private Long id;

	@Column(name = "NM_FORMULA")
	private String nome;

	@Column(name = "DS_FORMULA")
	private String evalFormula;

	@Column(name = "FL_ATIVO")
	private String situacao;

	@Column(name = "CD_LOGIN_INC")
	private String responsavelInclusao;

	@Column(name = "CD_LOGIN_ALT")
	private String responsavelAlteracao;

	@Column(name = "DT_INC")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime inclusao;

	@Column(name = "DT_ALT")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime alteracao;

	public FormulaModel() {
	}

	public FormulaModel(Long id) {
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

	public String getEvalFormula() {
		return evalFormula;
	}

	public void setEvalFormula(String evalFormula) {
		this.evalFormula = evalFormula;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getResponsavelInclusao() {
		return responsavelInclusao;
	}

	public void setResponsavelInclusao(String responsavelInclusao) {
		this.responsavelInclusao = responsavelInclusao;
	}

	public String getResponsavelAlteracao() {
		return responsavelAlteracao;
	}

	public void setResponsavelAlteracao(String responsavelAlteracao) {
		this.responsavelAlteracao = responsavelAlteracao;
	}

	public LocalDateTime getInclusao() {
		return inclusao;
	}

	public void setInclusao(LocalDateTime inclusao) {
		this.inclusao = inclusao;
	}

	public LocalDateTime getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(LocalDateTime alteracao) {
		this.alteracao = alteracao;
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
		FormulaModel other = (FormulaModel) obj;
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
