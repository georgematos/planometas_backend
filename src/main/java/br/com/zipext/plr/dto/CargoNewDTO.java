package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zipext.plr.model.CargoModel;
import br.com.zipext.plr.model.EquivalenciaModel;
import br.com.zipext.plr.utils.PLRUtils;

public class CargoNewDTO {

	private Long id;
	private String nome;
	private Long equivalenciaId;
	private String situacao;

	@JsonIgnore
	private boolean isNewCargo;

	public CargoNewDTO() {
	}

	public CargoNewDTO(CargoModel model) {
		this.id = model.getId();
		this.nome = model.getNome();
		this.equivalenciaId = model.getEquivalencia().getId();
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

	public Long getEquivalencia() {
		return equivalenciaId;
	}

	public void setEquivalencia(Long equivalenciaId) {
		this.equivalenciaId = equivalenciaId;
	}

	public boolean getIsNewCargo() {
		return isNewCargo;
	}

	public void setIsNewCargo(boolean isNewCargo) {
		this.isNewCargo = isNewCargo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public CargoModel obterModel() {
		CargoModel cargoModel = new CargoModel();
		EquivalenciaModel equivalenciaModel = new EquivalenciaModel();

		if (this.id != null)
			cargoModel.setId(this.id);
		if (this.nome != null)
			cargoModel.setNome(this.nome);
		if (this.equivalenciaId != null)
			equivalenciaModel.setId(this.equivalenciaId);
		if (this.situacao != null)
			cargoModel.setSituacao(this.situacao);
		if (this.id == null)
			cargoModel.setInclusao(LocalDateTime.now());

		cargoModel.setEquivalencia(equivalenciaModel);
		cargoModel.setResponsavelInclusao(PLRUtils.SYS_USER);

		return cargoModel;
	}
}
