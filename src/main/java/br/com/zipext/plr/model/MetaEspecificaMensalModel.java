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

@Entity
@Table(schema = "BET_PLR", name = "ASS_META_ESPECIF_MES")
public class MetaEspecificaMensalModel {

	@EmbeddedId
	private MetaEspecificaMensalPK pk;
	
	@Column(name = "VAL_PLAN")
	private BigDecimal valorMeta;
	
	@Column(name =  "VAL_REALIZADO")
	private BigDecimal valorRealizado;
	
	public MetaEspecificaMensalModel() {}
	
	public MetaEspecificaMensalModel(ColaboradorMetaEspecificaModel colaboradorMetaEspecifica, MesModel mes) {
		this.pk = new MetaEspecificaMensalPK(colaboradorMetaEspecifica, mes);
	}
	
	@Embeddable
	public static class MetaEspecificaMensalPK implements Serializable {
		
		private static final long serialVersionUID = 6371627271927814362L;

		@ManyToOne
		@JoinColumn(name = "CD_MATRICULA")
		@JoinColumn(name = "CD_META")
		@JoinColumn(name = "NU_SEQUENCIA")
		private ColaboradorMetaEspecificaModel colaboradorMetaEspecifica;
		
		@ManyToOne
		@JoinColumn(name = "CD_MES")
		private MesModel mes;
		
		public MetaEspecificaMensalPK() {}
		
		public MetaEspecificaMensalPK(ColaboradorMetaEspecificaModel colaboradorMetaEspecifica, MesModel mes) {
			this.colaboradorMetaEspecifica = colaboradorMetaEspecifica;
			this.mes = mes;
		}

		public ColaboradorMetaEspecificaModel getColaboradorMetaEspecifica() {
			return colaboradorMetaEspecifica;
		}

		public void setColaboradorMetaEspecifica(ColaboradorMetaEspecificaModel colaboradorMetaEspecifica) {
			this.colaboradorMetaEspecifica = colaboradorMetaEspecifica;
		}

		public MesModel getMes() {
			return mes;
		}

		public void setMes(MesModel mes) {
			this.mes = mes;
		}
	}

	public MetaEspecificaMensalPK getPk() {
		return pk;
	}

	public void setPk(MetaEspecificaMensalPK pk) {
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
}
