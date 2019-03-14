package br.com.zipext.plr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(schema = "BET_PLR", name = "ASS_HISTORICO_META_ESPECIF_MES")
public class HistoricoMetaEspecificaMensalModel {
	
	@EmbeddedId
	private HistoricoMetaEspecificaMensalPK pk;
	
	@Column(name = "VAL_PLAN")
	private BigDecimal valorMeta;
	
	@Column(name =  "VAL_REALIZADO")
	private BigDecimal valorRealizado;
	
	public HistoricoMetaEspecificaMensalModel() {}
	
	public HistoricoMetaEspecificaMensalModel(HistoricoModel historico, MetaEspecificaMensalModel metaEspecificaMensal) {
		BeanUtils.copyProperties(metaEspecificaMensal, this);
		this.pk = new HistoricoMetaEspecificaMensalPK(historico, metaEspecificaMensal);
	}
	
	@Embeddable
	public static class HistoricoMetaEspecificaMensalPK implements Serializable {

		private static final long serialVersionUID = -6830179354688643044L;
		
		@ManyToOne
		@JoinColumn(name = "CD_MATRICULA")
		@JoinColumn(name = "CD_META")
		@JoinColumn(name = "NU_SEQUENCIA")
		@JoinColumn(name = "CD_MES")
		private MetaEspecificaMensalModel metaEspecificaMensal;
		
		@ManyToOne
		@JoinColumn(name = "CD_HISTORICO")
		private HistoricoModel historico;
		
		public HistoricoMetaEspecificaMensalPK() {}
		
		public HistoricoMetaEspecificaMensalPK(HistoricoModel historico, MetaEspecificaMensalModel metaEspecificaMensal) {
			this.historico = historico;
			this.metaEspecificaMensal = metaEspecificaMensal;
		}

		public MetaEspecificaMensalModel getMetaEspecificaMensal() {
			return metaEspecificaMensal;
		}

		public void setMetaEspecificaMensal(MetaEspecificaMensalModel metaEspecificaMensal) {
			this.metaEspecificaMensal = metaEspecificaMensal;
		}

		public HistoricoModel getHistorico() {
			return historico;
		}

		public void setHistorico(HistoricoModel historico) {
			this.historico = historico;
		}
	}

	public HistoricoMetaEspecificaMensalPK getPk() {
		return pk;
	}

	public void setPk(HistoricoMetaEspecificaMensalPK pk) {
		this.pk = pk;
	}

	public BigDecimal getValorMeta() {
		return valorMeta;
	}

	public void setValorMeta(BigDecimal valorMeta) {
		this.valorMeta = valorMeta;
	}

	public BigDecimal getValorRealizado() {
		return valorRealizado;
	}

	public void setValorRealizado(BigDecimal valorRealizado) {
		this.valorRealizado = valorRealizado;
	}
	
	public Integer getNumMes() {
		return this.pk.getMetaEspecificaMensal().getNumMes();
	}
	
	public double getValorMetaAsDouble() {
		return this.valorMeta != null ? this.valorMeta.doubleValue() : 0.0;
	}
	
	public double getValorRealizadoAsDouble() {
		return this.valorRealizado != null ? this.valorRealizado.doubleValue() : 0.0;
	}
}
