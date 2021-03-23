package br.com.zipext.plr.dto;

import java.math.BigDecimal;

import br.com.zipext.plr.model.AvaliacaoProjetoOrcModel;
import br.com.zipext.plr.model.AvaliacaoProjetoPrazoModel;
import br.com.zipext.plr.model.AvaliacaoProjetoQualiModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel;
import br.com.zipext.plr.model.RelAvaliacaoProjetoModel.RelAvaliacaoProjetoPK;
import br.com.zipext.plr.model.RelMetaAvaliacaoProjetoModel;
import br.com.zipext.plr.model.RelMetaAvaliacaoProjetoModel.RelMetaAvaliacaoProjetoPK;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.utils.PLRUtils;

public class RelMetaAvaliacaoProjetoDTO {
	
	private BigDecimal valEscalonamento;
	private String comentarios;
	private ColaboradorDTO responsavel;
	private String dataAvaliacao;
	private GenericDTO meta;
	private RelAvaliacaoProjetoDTO avaliacaoProjeto;

	public RelMetaAvaliacaoProjetoDTO() {}
	
	public RelMetaAvaliacaoProjetoDTO(RelMetaAvaliacaoProjetoModel model) {
		this.meta = new GenericDTO(model.getPk().getMeta());
		this.avaliacaoProjeto = new RelAvaliacaoProjetoDTO(model.getPk().getRelAvaliacaoProjeto());
		this.dataAvaliacao = model.getDataAvaliacao().getDescricao();
		this.responsavel = new ColaboradorDTO(model.getResponsavel());
		this.valEscalonamento = model.getValEscalonamento();
		this.comentarios = model.getComentarios();
	}
	
	public RelMetaAvaliacaoProjetoModel obterModel() {
		RelMetaAvaliacaoProjetoModel model = new RelMetaAvaliacaoProjetoModel();
		RelMetaAvaliacaoProjetoPK modelPK = new RelMetaAvaliacaoProjetoPK();
		
		RelAvaliacaoProjetoModel avalProjetoModel = new RelAvaliacaoProjetoModel();
		RelAvaliacaoProjetoPK avalProjetoPK = new RelAvaliacaoProjetoPK();
		
		AvaliacaoProjetoPrazoModel prazo = new AvaliacaoProjetoPrazoModel(this.avaliacaoProjeto.getAvaliacaoPrazo().getId());
		AvaliacaoProjetoQualiModel qualidade = new AvaliacaoProjetoQualiModel(this.avaliacaoProjeto.getAvaliacaoQualidade().getId());
		AvaliacaoProjetoOrcModel orcamento = new AvaliacaoProjetoOrcModel(this.avaliacaoProjeto.getAvaliacaoOrcamento().getId());
		
		avalProjetoPK.setAvaliacaoProjetoPrazo(prazo);
		avalProjetoPK.setAvaliacaoProjetoQuali(qualidade);
		avalProjetoPK.setAvaliacaoProjetoOrc(orcamento);
		
		avalProjetoModel.setPk(avalProjetoPK);
		
		modelPK.setMeta(new MetasModel(this.meta.getId()));
		modelPK.setRelAvaliacaoProjeto(avalProjetoModel);
		
		model.setValEscalonamento(this.valEscalonamento);
		model.setComentarios(this.comentarios);
		model.setResponsavel(new ColaboradorModel(this.responsavel.getMatricula()));
		model.setDataAvaliacao(new TempoModel(PLRUtils.getSkyTempoFromStringDate(this.dataAvaliacao)));
		model.setPk(modelPK);
		
		return
				model;
	}

	public BigDecimal getValEscalonamento() {
		return valEscalonamento;
	}

	public void setValEscalonamento(BigDecimal valEscalonamento) {
		this.valEscalonamento = valEscalonamento;
	}
	

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public ColaboradorDTO getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ColaboradorDTO responsavel) {
		this.responsavel = responsavel;
	}

	public String getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(String dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public GenericDTO getMeta() {
		return meta;
	}

	public void setMeta(GenericDTO meta) {
		this.meta = meta;
	}

	public RelAvaliacaoProjetoDTO getAvaliacaoProjeto() {
		return avaliacaoProjeto;
	}

	public void setAvaliacaoProjeto(RelAvaliacaoProjetoDTO avaliacaoProjeto) {
		this.avaliacaoProjeto = avaliacaoProjeto;
	}
}
