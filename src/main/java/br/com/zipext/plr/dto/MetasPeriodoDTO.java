package br.com.zipext.plr.dto;

import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.MetasPeriodoModel;
import br.com.zipext.plr.model.MetasPeriodoModel.MetasPeriodoPK;
import br.com.zipext.plr.model.TempoModel;

public class MetasPeriodoDTO {
	
	private GenericDTO meta;
	
	private TempoDTO tempo;
	
	private String situacao;
	
	public MetasPeriodoDTO() {}
	
	public MetasPeriodoDTO(MetasPeriodoModel model) {
		if (model != null) {
			this.meta = new GenericDTO(model.getPk().getMetas());
			this.tempo = new TempoDTO(model.getPk().getTempo());
			this.situacao = model.getSituacao();
		}
	}
	
	public MetasPeriodoModel obterModel() {
		MetasPeriodoModel model = new MetasPeriodoModel();
		
		model.setPk(new MetasPeriodoPK(new MetasModel(this.meta.getId()), new TempoModel(this.tempo.getId())));
		model.setSituacao(this.situacao);
		
		return 
				model;
	}
	

	public GenericDTO getMeta() {
		return meta;
	}

	public void setMeta(GenericDTO meta) {
		this.meta = meta;
	}

	public TempoDTO getTempo() {
		return tempo;
	}

	public void setTempo(TempoDTO tempo) {
		this.tempo = tempo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
