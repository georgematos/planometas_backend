package br.com.zipext.plr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "METAS", name = "ASS_CAD_AVAL_META")
public class RelMetaAvaliacaoProjetoModel {

	@EmbeddedId
	private RelMetaAvaliacaoProjetoPK pk;
	
	@Column(name = "VAL_ESCALONAMENTO")
	private BigDecimal valEscalonamento;
	
	@Column(name = "DS_COMENTARIOS")
	private String comentarios;
	
	@ManyToOne
	@JoinColumn(name = "CD_RESPONSAVEL")
	private ColaboradorModel responsavel;
	
	@ManyToOne
	@JoinColumn(name = "SKY_AVALIACAO")
	private TempoModel dataAvaliacao;
	
	public RelMetaAvaliacaoProjetoModel() {}
	
	public RelMetaAvaliacaoProjetoModel(MetasModel meta, RelAvaliacaoProjetoModel relAvaliacaoProjeto) {
		this.pk = new RelMetaAvaliacaoProjetoPK(meta, relAvaliacaoProjeto);
	}
	
	public RelMetaAvaliacaoProjetoPK getPk() {
		return pk;
	}

	public void setPk(RelMetaAvaliacaoProjetoPK pk) {
		this.pk = pk;
	}

	public ColaboradorModel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ColaboradorModel responsavel) {
		this.responsavel = responsavel;
	}
	
	public TempoModel getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(TempoModel dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
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
		RelMetaAvaliacaoProjetoModel other = (RelMetaAvaliacaoProjetoModel) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}



	@Embeddable
	public static class RelMetaAvaliacaoProjetoPK implements Serializable {

		private static final long serialVersionUID = -3090124878778905323L;
		
		@ManyToOne
		@JoinColumn(name = "CD_META")
		private MetasModel meta;
		
		@ManyToOne
		@JoinColumns( {
		    @JoinColumn(name="CD_AVAL_META_PRAZO", referencedColumnName="CD_AVAL_META_PRAZO"),
		    @JoinColumn(name="CD_AVAL_META_QUAL", referencedColumnName="CD_AVAL_META_QUAL"),
		    @JoinColumn(name="CD_AVAL_META_ORC", referencedColumnName="CD_AVAL_META_ORC"),
		} )
		private RelAvaliacaoProjetoModel relAvaliacaoProjeto;
		
		public RelMetaAvaliacaoProjetoPK() {}
		
		public RelMetaAvaliacaoProjetoPK(MetasModel meta, RelAvaliacaoProjetoModel relAvaliacaoProjeto) {
			this.meta = meta;
			this.relAvaliacaoProjeto = relAvaliacaoProjeto;
		}

		public MetasModel getMeta() {
			return meta;
		}

		public void setMeta(MetasModel meta) {
			this.meta = meta;
		}

		public RelAvaliacaoProjetoModel getRelAvaliacaoProjeto() {
			return relAvaliacaoProjeto;
		}

		public void setRelAvaliacaoProjeto(RelAvaliacaoProjetoModel relAvaliacaoProjeto) {
			this.relAvaliacaoProjeto = relAvaliacaoProjeto;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((meta == null) ? 0 : meta.hashCode());
			result = prime * result + ((relAvaliacaoProjeto == null) ? 0 : relAvaliacaoProjeto.hashCode());
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
			RelMetaAvaliacaoProjetoPK other = (RelMetaAvaliacaoProjetoPK) obj;
			if (meta == null) {
				if (other.meta != null)
					return false;
			} else if (!meta.equals(other.meta))
				return false;
			if (relAvaliacaoProjeto == null) {
				if (other.relAvaliacaoProjeto != null)
					return false;
			} else if (!relAvaliacaoProjeto.equals(other.relAvaliacaoProjeto))
				return false;
			return true;
		}
	}
}
