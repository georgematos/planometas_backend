package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FormulaModel;
import br.com.zipext.plr.utils.PLRUtils;

public class FormulaDTO {

	private Long id;

	private String nome;

	private String evalFormula;

	private String situacao;

	private boolean isNewFormula;

	public FormulaDTO() {
	}

	public FormulaDTO(FormulaModel model) {
		BeanUtils.copyProperties(model, this);
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

	public boolean getIsNewFormula() {
		return isNewFormula;
	}

	public void setIsNewFormula(boolean isNewFormula) {
		this.isNewFormula = isNewFormula;
	}

	public FormulaModel obterModel() {
		FormulaModel formulaModel = new FormulaModel();

		if (this.id != null)
			formulaModel.setId(this.id);
		if (this.nome != null)
			formulaModel.setNome(this.nome);
		if (this.evalFormula != null)
			formulaModel.setEvalFormula(this.evalFormula);
		if (this.situacao != null)
			formulaModel.setSituacao(this.situacao);
		if (this.id == null)
			formulaModel.setInclusao(LocalDateTime.now());

		formulaModel.setResponsavelInclusao(PLRUtils.SYS_USER);

		return formulaModel;
	}

}
