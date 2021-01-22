package br.com.zipext.plr.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.TimeModel;
import br.com.zipext.plr.utils.PLRUtils;

public class TimeDTO {

	private String codigo;
	private String nome;
	private boolean isNewTime;

	public TimeDTO() {
	}

	public TimeDTO(TimeModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getIsNewTime() {
		return isNewTime;
	}

	public void setIsNewTime(boolean isNewTime) {
		this.isNewTime = isNewTime;
	}

	public TimeModel obterModel() {
		TimeModel filialModel = new TimeModel();

		if (this.codigo != null)
			filialModel.setCodigo(this.codigo.toUpperCase());
		if (this.nome != null)
			filialModel.setNome(this.getNome().toUpperCase());
		if (this.getIsNewTime()) {
			filialModel.setInclusao(LocalDateTime.now());
			filialModel.setResponsavelInclusao(PLRUtils.SYS_USER);
		} else {
			filialModel.setAlteracao(LocalDateTime.now());
			filialModel.setResponsavelAlteracao(PLRUtils.SYS_USER);
		}

		return filialModel;
	}
}
