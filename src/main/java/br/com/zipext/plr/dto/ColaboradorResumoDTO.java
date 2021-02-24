package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.utils.PLRUtils;

public class ColaboradorResumoDTO {

	private String matricula;

	private String nome;

	private Long cargoId;

	private String cargoNome;

	public ColaboradorResumoDTO() {
	}

	public ColaboradorResumoDTO(ColaboradorModel model) {
		BeanUtils.copyProperties(model, this);
		this.setCargoId(model.getCargo().getId());
		this.setCargoNome(model.getCargo().nome);
	}

	public ColaboradorModel obterModel() {
		ColaboradorModel model = new ColaboradorModel();
		BeanUtils.copyProperties(this, model);

		model.setInclusao(LocalDateTime.now());
		model.setResponsavelInclusao(PLRUtils.SYS_USER);

		return model;
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

	public Long getCargoId() {
		return cargoId;
	}

	public void setCargoId(Long cargoId) {
		this.cargoId = cargoId;
	}

	public String getCargoNome() {
		return cargoNome;
	}

	public void setCargoNome(String cargoNome) {
		this.cargoNome = cargoNome;
	}

}
