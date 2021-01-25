package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.DiretoriaModel;
import br.com.zipext.plr.utils.PLRUtils;

public class DiretoriaDTO {

	private Long id;

	private String nome;

	public DiretoriaDTO() {
	}

	public DiretoriaDTO(DiretoriaModel model) {
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

	public DiretoriaModel obterModel() {
		DiretoriaModel diretoriaModel = new DiretoriaModel();

		if (this.id != null)
			diretoriaModel.setId(this.id);
		if (this.nome != null)
			diretoriaModel.setNome(this.getNome().toUpperCase());
		if (this.id == null) {
			diretoriaModel.setInclusao(LocalDateTime.now());
			diretoriaModel.setResponsavelInclusao(PLRUtils.SYS_USER);
		} else {
			diretoriaModel.setAlteracao(LocalDateTime.now());
			diretoriaModel.setResponsavelAlteracao(PLRUtils.SYS_USER);
		}

		return diretoriaModel;
	}
}
