package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.TipoMedicaoModel;

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
}
