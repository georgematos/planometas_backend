package br.com.zipext.plr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(schema = "BET_PLR", name = "ASS_HISTORICO_META_ESPECIF")
public class HistoricoMetaEspecificaModel {
	
	@EmbeddedId
	private HistoricoMetaEspecificaPK pk;
	
	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	@Column(name = "DS_FREQUENCIA_MED")
	private String frequenciaMedicao;
	
	@Column(name = "DS_TIPO_META")
	private String tipoMeta;
	
	@Column(name = "DS_DESCRICAO")
	private String descricao;

	@Column(name = "VAL_META")
	private BigDecimal valMeta;
	
	@Column(name = "QT_PESO")
	private BigDecimal peso;

	@Column(name = "DT_PRAZO")
	private LocalDate prazo;
	
	@Column(name = "DT_INC")
	private LocalDateTime dataInclusao;
	
	@Column(name = "CD_LOGIN_INC")
	private String responsavel;

	public HistoricoMetaEspecificaModel() {}
	
	public HistoricoMetaEspecificaModel(HistoricoMetaEspecificaPK pk) {
		this.pk = pk;
	}
	
	public HistoricoMetaEspecificaModel(HistoricoModel historico, ColaboradorMetaEspecificaModel colaboradorMetaEspecifica) {
		BeanUtils.copyProperties(colaboradorMetaEspecifica, this);
		this.dataInclusao = LocalDateTime.now();
		this.pk = new HistoricoMetaEspecificaPK(historico, colaboradorMetaEspecifica);
	}
	
	@Embeddable
	public static class HistoricoMetaEspecificaPK implements Serializable {

		private static final long serialVersionUID = -7952452412792713048L;
	
		@ManyToOne
		@JoinColumn(name = "CD_HISTORICO")
		private HistoricoModel historico;
		
		@ManyToOne
		@JoinColumn(name = "CD_META", referencedColumnName = "CD_META", nullable = false) 
		@JoinColumn(name = "CD_MATRICULA", referencedColumnName = "CD_MATRICULA", nullable = false)
		@JoinColumn(name = "NU_SEQUENCIA", referencedColumnName = "NU_SEQUENCIA", nullable = false)
		private ColaboradorMetaEspecificaModel colaboradorMetaEspecifica;
		
		public HistoricoMetaEspecificaPK() {}
		
		public HistoricoMetaEspecificaPK(HistoricoModel historico, ColaboradorMetaEspecificaModel colaboradorMetaEspecifica) {
			this.historico = historico;
			this.colaboradorMetaEspecifica = colaboradorMetaEspecifica;
		}

		public HistoricoModel getHistorico() {
			return historico;
		}

		public void setHistorico(HistoricoModel historico) {
			this.historico = historico;
		}

		public ColaboradorMetaEspecificaModel getColaboradorMetaEspecifica() {
			return colaboradorMetaEspecifica;
		}

		public void setColaboradorMetaEspecifica(ColaboradorMetaEspecificaModel colaboradorMetaEspecifica) {
			this.colaboradorMetaEspecifica = colaboradorMetaEspecifica;
		}
	}

	public HistoricoMetaEspecificaPK getPk() {
		return pk;
	}

	public void setPk(HistoricoMetaEspecificaPK pk) {
		this.pk = pk;
	}

	public BigDecimal getValMeta() {
		return valMeta;
	}

	public void setValMeta(BigDecimal valMeta) {
		this.valMeta = valMeta;
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getFrequenciaMedicao() {
		return frequenciaMedicao;
	}

	public void setFrequenciaMedicao(String frequenciaMedicao) {
		this.frequenciaMedicao = frequenciaMedicao;
	}

	public String getTipoMeta() {
		return tipoMeta;
	}

	public void setTipoMeta(String tipoMeta) {
		this.tipoMeta = tipoMeta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
}
