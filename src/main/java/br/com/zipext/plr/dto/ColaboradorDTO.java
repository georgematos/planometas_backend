package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.ColaboradorModel;

public class ColaboradorDTO {
	
	private String matricula;
	
	private String nome;
	
	private String cargo;
	
	private String diretoria;
	
	public ColaboradorDTO() {}
	
	public ColaboradorDTO(ColaboradorModel model) {
		BeanUtils.copyProperties(model, this);
		this.cargo = model.getCargo().getNome();
		this.diretoria = model.getDiretoria().getNome();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(String diretoria) {
		this.diretoria = diretoria;
	}
}
