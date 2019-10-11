package br.com.zipext.plr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

	public FormulaModel() {}
	
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
