package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.utils.PLRUtils;

public class TipoMedicaoDTO {
	
	private Long id;
	private String descricao;
	
	public TipoMedicaoDTO() {}
	
	public TipoMedicaoDTO(TipoMedicaoModel model) {
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
	
	public TipoMedicaoModel obterModel() {
		TipoMedicaoModel tipoMedicaoModel = new TipoMedicaoModel();

		if (this.id != null)
			tipoMedicaoModel.setId(this.id);
		if (this.descricao != null)
			tipoMedicaoModel.setDescricao(this.getDescricao().toUpperCase());
		if (this.id == null) {
			tipoMedicaoModel.setInclusao(LocalDateTime.now());
			tipoMedicaoModel.setResponsavelInclusao(PLRUtils.SYS_USER);
		} else {
			tipoMedicaoModel.setAlteracao(LocalDateTime.now());
			tipoMedicaoModel.setResponsavelAlteracao(PLRUtils.SYS_USER);
		}

		return tipoMedicaoModel;

	}
}
