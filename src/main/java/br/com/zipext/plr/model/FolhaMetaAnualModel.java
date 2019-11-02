package br.com.zipext.plr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "FAT", name = "FAT_META_ANUAL")
public class FolhaMetaAnualModel {

	@EmbeddedId
	private FolhaMetaAnualPK pk;
	
	@Column(name = "VAL_META_AGG") 
	private BigDecimal valorMeta;

	@Column(name = "VAL_REAL_AGG")
	private BigDecimal valorRealizado;
	
	@Column(name =  "VAL_PERC_ATINGIDO")
	private BigDecimal valorPercentAtingido;
	
	@Transient
	private BigDecimal desempenho;
	
	public FolhaMetaAnualModel() {}
	
	public FolhaMetaAnualModel(MetasModel meta, TempoModel tempo) {
		this.pk = new FolhaMetaAnualPK(meta, tempo);
	}
	
	public FolhaMetaAnualPK getPk() {
		return pk;
	}

	public void setPk(FolhaMetaAnualPK pk) {
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

	public BigDecimal getValorPercentAtingido() {
		return valorPercentAtingido;
	}

	public void setValorPercentAtingido(BigDecimal valorPercentAtingido) {
		this.valorPercentAtingido = valorPercentAtingido;
	}
	
	public BigDecimal getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(BigDecimal desempenho) {
		this.desempenho = desempenho;
	}
	
	public BigDecimal calcDesempenho() {
		return 
				this.valorRealizado.divide(this.valorMeta, 4, RoundingMode.CEILING).multiply(new BigDecimal(100));
	}
	
	public boolean isMetaRestrita() {
		return
				this.pk.getMeta().getTipoMeta().isMetaRestrita();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FolhaMetaAnualModel other = (FolhaMetaAnualModel) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Embeddable
	public static class FolhaMetaAnualPK implements Serializable {

		private static final long serialVersionUID = 5415383410823735671L;
		
		@ManyToOne
		@JoinColumn(name = "CD_META")
		private MetasModel meta;
		
		@ManyToOne
		@JoinColumn(name = "SKY_ANO")
		private TempoModel tempo;
		
		public FolhaMetaAnualPK() {}
		
		public FolhaMetaAnualPK(MetasModel meta, TempoModel tempo) {
			this.meta = meta;
			this.tempo = tempo;
		}

		public MetasModel getMeta() {
			return meta;
		}

		public void setMeta(MetasModel meta) {
			this.meta = meta;
		}

		public TempoModel getTempo() {
			return tempo;
		}

		public void setTempo(TempoModel tempo) {
			this.tempo = tempo;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((meta == null) ? 0 : meta.hashCode());
			result = prime * result + ((tempo == null) ? 0 : tempo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FolhaMetaAnualPK other = (FolhaMetaAnualPK) obj;
			if (meta == null) {
				if (other.meta != null)
					return false;
			} else if (!meta.equals(other.meta))
				return false;
			if (tempo == null) {
				if (other.tempo != null)
					return false;
			} else if (!tempo.equals(other.tempo))
				return false;
			return true;
		}
	}
}
