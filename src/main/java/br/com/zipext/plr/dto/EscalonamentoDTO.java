package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.EscalonamentoModel;
import br.com.zipext.plr.model.TipoMedicaoModel;
import br.com.zipext.plr.utils.PLRUtils;

public class EscalonamentoDTO {

	private Long id;
	private TipoMedicaoModel tipoMedicao;
	private Long tipoMedicaoId;
	private BigDecimal faixa;
	private BigDecimal desempenho;
	private boolean isNewEscalonamento;

	public EscalonamentoDTO() {
	}

	public EscalonamentoDTO(EscalonamentoModel model) {
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

	public TipoMedicaoModel getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(TipoMedicaoModel tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public BigDecimal getFaixa() {
		return faixa;
	}

	public void setFaixa(BigDecimal faixa) {
		this.faixa = faixa;
	}

	public BigDecimal getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(BigDecimal desempenho) {
		this.desempenho = desempenho;
	}

	public boolean getIsNewEscalonamento() {
		return isNewEscalonamento;
	}

	public void setIsNewEscalonamento(boolean isNewEscalonamento) {
		this.isNewEscalonamento = isNewEscalonamento;
	}

	public Long getTipoMedicaoId() {
		return tipoMedicaoId;
	}

	public void setTipoMedicaoId(Long tipoMedicaoId) {
		this.tipoMedicaoId = tipoMedicaoId;
	}

	public EscalonamentoModel obterModel() {
		EscalonamentoModel escalonamentoModel = new EscalonamentoModel();

		if (this.id != null)
			escalonamentoModel.setId(this.id);
		if (this.tipoMedicao != null)
			escalonamentoModel.setTipoMedicao(this.tipoMedicao);
		if (this.faixa != null)
			escalonamentoModel.setFaixa(this.faixa);
		if (this.desempenho != null)
			escalonamentoModel.setDesempenho(this.desempenho);
		if (this.getIsNewEscalonamento()) {
			escalonamentoModel.setInclusao(LocalDateTime.now());
			escalonamentoModel.setResponsavelInclusao(PLRUtils.SYS_USER);
		} else {
			escalonamentoModel.setAlteracao(LocalDateTime.now());
			escalonamentoModel.setResponsavelAlteracao(PLRUtils.SYS_USER);
		}

		return escalonamentoModel;
	}

}
