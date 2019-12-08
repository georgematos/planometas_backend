package br.com.zipext.plr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "METAS", name = "ASS_PERIODO_META")
public class MetasPeriodoModel {

	@EmbeddedId
	private MetasPeriodoPK pk;
	
	@Column(name = "FL_ATIVO")
	private String situacao;
	
	public MetasPeriodoModel() {}
	
	public MetasPeriodoModel(MetasModel metas, TempoModel tempo) {
		this.pk = new MetasPeriodoPK(metas, tempo);
	}
	
	public MetasPeriodoPK getPk() {
		return pk;
	}

	public void setPk(MetasPeriodoPK pk) {
		this.pk = pk;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
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
		MetasPeriodoModel other = (MetasPeriodoModel) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}



	@Embeddable
	public static class MetasPeriodoPK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6567298803678473754L;
		
		@ManyToOne
		@JoinColumn(name = "CD_META")
		private MetasModel metas;
		
		@ManyToOne
		@JoinColumn(name = "SKY_PERIODO_META")
		private TempoModel tempo;
		
		public MetasPeriodoPK() {}
		
		public MetasPeriodoPK(MetasModel metas, TempoModel tempo) {
			this.metas = metas;
			this.tempo = tempo;
		}

		public MetasModel getMetas() {
			return metas;
		}

		public void setMetas(MetasModel metas) {
			this.metas = metas;
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
			result = prime * result + ((metas == null) ? 0 : metas.hashCode());
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
			MetasPeriodoPK other = (MetasPeriodoPK) obj;
			if (metas == null) {
				if (other.metas != null)
					return false;
			} else if (!metas.equals(other.metas))
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
