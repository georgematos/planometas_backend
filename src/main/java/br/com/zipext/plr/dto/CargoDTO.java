package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.CargoModel;

public class CargoDTO {
	
	private Long id;
	private String nome;
	private EquivalenciaDTO equivalencia;

	public CargoDTO() {}
	
	public CargoDTO(CargoModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			this.equivalencia = new EquivalenciaDTO(model.getEquivalencia());
		}
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

	public EquivalenciaDTO getEquivalencia() {
		return equivalencia;
	}

	public void setEquivalencia(EquivalenciaDTO equivalencia) {
		this.equivalencia = equivalencia;
	}
}
