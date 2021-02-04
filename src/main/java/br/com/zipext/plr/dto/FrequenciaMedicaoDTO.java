package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FrequenciaMedicaoModel;
import br.com.zipext.plr.utils.PLRUtils;

public class FrequenciaMedicaoDTO {

	private Long id;
	private String descricao;

	public FrequenciaMedicaoDTO() {
	}

	public FrequenciaMedicaoDTO(FrequenciaMedicaoModel model) {
		BeanUtils.copyProperties(model, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public FrequenciaMedicaoModel obterModel() {
		FrequenciaMedicaoModel frequenciaMedicaoModel = new FrequenciaMedicaoModel();

		if (this.id != null)
			frequenciaMedicaoModel.setId(this.id);
		if (this.descricao != null)
			frequenciaMedicaoModel.setDescricao(this.getDescricao().toUpperCase());
		if (this.id == null) {
			frequenciaMedicaoModel.setInclusao(LocalDateTime.now());
			frequenciaMedicaoModel.setResponsavelInclusao(PLRUtils.SYS_USER);
		} else {
			frequenciaMedicaoModel.setAlteracao(LocalDateTime.now());
			frequenciaMedicaoModel.setResponsavelAlteracao(PLRUtils.SYS_USER);
		}

		return frequenciaMedicaoModel;

	}
}
