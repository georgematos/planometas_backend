package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FilialModel;
import br.com.zipext.plr.utils.PLRUtils;

public class FilialDTO {

	private Long id;

	private String nome;

	public FilialDTO() {
	}

	public FilialDTO(FilialModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
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

	public FilialModel obterModel() {
		FilialModel filialModel = new FilialModel();

		if (this.id != null)
			filialModel.setId(this.id);
		if (this.nome != null)
			filialModel.setNome(this.getNome().toUpperCase());
		if (this.id == null) {
			filialModel.setInclusao(LocalDateTime.now());
			filialModel.setResponsavelInclusao(PLRUtils.SYS_USER);
		} else {
			filialModel.setAlteracao(LocalDateTime.now());
			filialModel.setResponsavelAlteracao(PLRUtils.SYS_USER);
		}

		return filialModel;
	}
}
