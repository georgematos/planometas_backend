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
import javax.persistence.Transient;

@Entity
@Table(schema = "METAS", name = "ASS_META_EQUIV_PERIODO")
public class MetaEquivalenciaPeriodoModel {

	@EmbeddedId
	private MetaEquivalenciaPeriodoPK pk;
			
	@Column(name = "FL_ATIVO")
	private String situacao;
	
	@Column(name = "VAL_PESO")
	private BigDecimal peso;
	
	@Transient
	private boolean isReadOnly;
	
	public MetaEquivalenciaPeriodoModel() {}
	
	public MetaEquivalenciaPeriodoModel(MetasModel meta, EquivalenciaModel equivalencia, TempoModel tempo) {
		this.pk = new MetaEquivalenciaPeriodoPK(meta, equivalencia, tempo);
		this.isReadOnly = true; //Usado no JSGrid, para indicar que a meta é sugerida e não pode ser editada.
	}

	public MetaEquivalenciaPeriodoPK getPk() {
		return pk;
	}

	public void setPk(MetaEquivalenciaPeriodoPK pk) {
		this.pk = pk;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	
	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
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
		MetaEquivalenciaPeriodoModel other = (MetaEquivalenciaPeriodoModel) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Embeddable
	public static class MetaEquivalenciaPeriodoPK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4102029987922069856L;
		
		@ManyToOne
		@JoinColumn(name = "CD_META")
		public MetasModel meta;
		
		@ManyToOne
		@JoinColumn(name = "CD_EQUIVALENCIA")
		public EquivalenciaModel equivalencia;
		
		@ManyToOne
		@JoinColumn(name = "SKY_PERIODO")
		public TempoModel tempo;
		
		public MetaEquivalenciaPeriodoPK() {}
		
		public MetaEquivalenciaPeriodoPK(MetasModel meta, EquivalenciaModel equivalencia, TempoModel tempo) {
			this.meta = meta;
			this.equivalencia = equivalencia;
			this.tempo = tempo;
		}

		public MetasModel getMeta() {
			return meta;
		}

		public void setMeta(MetasModel meta) {
			this.meta = meta;
		}

		public EquivalenciaModel getEquivalencia() {
			return equivalencia;
		}

		public void setEquivalencia(EquivalenciaModel equivalencia) {
			this.equivalencia = equivalencia;
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
			result = prime * result + ((equivalencia == null) ? 0 : equivalencia.hashCode());
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
			MetaEquivalenciaPeriodoPK other = (MetaEquivalenciaPeriodoPK) obj;
			if (equivalencia == null) {
				if (other.equivalencia != null)
					return false;
			} else if (!equivalencia.equals(other.equivalencia))
				return false;
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
