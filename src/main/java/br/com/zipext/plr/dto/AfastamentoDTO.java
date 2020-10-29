package br.com.zipext.plr.dto;

import org.apache.commons.lang3.StringUtils;

import br.com.zipext.plr.model.AfastamentoModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.model.AfastamentoModel.AfastamentoPK;
import br.com.zipext.plr.utils.PLRUtils;

public class AfastamentoDTO {
	
	private GenericDTO colaborador;
	private String inicioAfastamento;
	private String fimAfastamento;
	private String motivo;
	
	public AfastamentoDTO() {}
	
	public AfastamentoDTO(AfastamentoModel model) {
		this.colaborador = new GenericDTO(model.getPk().getColaborador());
		this.inicioAfastamento = model.getPk().getInicioAfastamento().getDescricao();
		this.motivo = model.getMotivo();
		if (model.getFimAfastamento() != null) {
			this.fimAfastamento = model.getFimAfastamento().getDescricao();
		}
	}
	
	public AfastamentoModel obterModel() {
		AfastamentoModel model = new AfastamentoModel();
		
		ColaboradorModel colaborador = new ColaboradorModel(this.colaborador.getMatricula());
		TempoModel inicioAfastamento = new TempoModel(PLRUtils.getSkyTempoFromStringDate(this.inicioAfastamento));
		AfastamentoPK pk = new AfastamentoPK(colaborador, inicioAfastamento);
		
		model.setPk(pk);
		model.setMotivo(this.motivo);
		if (StringUtils.isNotBlank(this.fimAfastamento)) {
			model.setFimAfastamento(new TempoModel(PLRUtils.getSkyTempoFromStringDate(this.fimAfastamento)));
		}
		
		return
				model;
	}
	
	public GenericDTO getColaborador() {
		return colaborador;
	}

	public void setColaborador(GenericDTO colaborador) {
		this.colaborador = colaborador;
	}

	public String getInicioAfastamento() {
		return inicioAfastamento;
	}

	public void setInicioAfastamento(String inicioAfastamento) {
		this.inicioAfastamento = inicioAfastamento;
	}

	public String getFimAfastamento() {
		return fimAfastamento;
	}

	public void setFimAfastamento(String fimAfastamento) {
		this.fimAfastamento = fimAfastamento;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
