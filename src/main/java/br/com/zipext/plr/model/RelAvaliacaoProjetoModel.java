package br.com.zipext.plr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zipext.plr.converter.LocalDateTimeConverter;

@Entity
@Table(schema = "METAS", name = "ASS_AVAL_META_PRAZO_QUAL")
public class RelAvaliacaoProjetoModel {
	
	@EmbeddedId
	private RelAvaliacaoProjetoModelPK pk;
	
	@Column(name = "VAL_ESCALONAMENTO")
	private BigDecimal valEscalonamento;

	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;
    
    @Column(name = "DT_ALT")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime alteracao;
    
    @Column(name = "CD_LOGIN_ALT")
    private String responsavelAlteracao;
    
    public RelAvaliacaoProjetoModel() {}
    
    public RelAvaliacaoProjetoModel(AvaliacaoProjetoPrazoModel avaliacaoProjetoPrazo, AvaliacaoProjetoQualiModel avaliacaoProjetoQuali) {
    	this.pk = new RelAvaliacaoProjetoModelPK(avaliacaoProjetoPrazo, avaliacaoProjetoQuali);
    }
	
	public RelAvaliacaoProjetoModelPK getPk() {
		return pk;
	}

	public void setPk(RelAvaliacaoProjetoModelPK pk) {
		this.pk = pk;
	}

	public BigDecimal getValEscalonamento() {
		return valEscalonamento;
	}

	public void setValEscalonamento(BigDecimal valEscalonamento) {
		this.valEscalonamento = valEscalonamento;
	}

	public LocalDateTime getInclusao() {
		return inclusao;
	}

	public void setInclusao(LocalDateTime inclusao) {
		this.inclusao = inclusao;
	}

	public String getResponsavelInclusao() {
		return responsavelInclusao;
	}

	public void setResponsavelInclusao(String responsavelInclusao) {
		this.responsavelInclusao = responsavelInclusao;
	}

	public LocalDateTime getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(LocalDateTime alteracao) {
		this.alteracao = alteracao;
	}

	public String getResponsavelAlteracao() {
		return responsavelAlteracao;
	}

	public void setResponsavelAlteracao(String responsavelAlteracao) {
		this.responsavelAlteracao = responsavelAlteracao;
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
		RelAvaliacaoProjetoModel other = (RelAvaliacaoProjetoModel) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Embeddable
	public static class RelAvaliacaoProjetoModelPK implements Serializable {

		private static final long serialVersionUID = 8780212793915401691L;
	
		@ManyToOne
		@JoinColumn(name = "CD_AVAL_META_PRAZO")
		private AvaliacaoProjetoPrazoModel avaliacaoProjetoPrazo;
		
		@ManyToOne
		@JoinColumn(name = "CD_AVAL_META_QUAL")
		private AvaliacaoProjetoQualiModel avaliacaoProjetoQuali;
		
		public RelAvaliacaoProjetoModelPK() {}
		
		public RelAvaliacaoProjetoModelPK(AvaliacaoProjetoPrazoModel avaliacaoProjetoPrazo, AvaliacaoProjetoQualiModel avaliacaoProjetoQuali) {
			this.avaliacaoProjetoPrazo = avaliacaoProjetoPrazo;
			this.avaliacaoProjetoQuali = avaliacaoProjetoQuali;
		}

		public AvaliacaoProjetoPrazoModel getAvaliacaoProjetoPrazo() {
			return avaliacaoProjetoPrazo;
		}

		public void setAvaliacaoProjetoPrazo(AvaliacaoProjetoPrazoModel avaliacaoProjetoPrazo) {
			this.avaliacaoProjetoPrazo = avaliacaoProjetoPrazo;
		}

		public AvaliacaoProjetoQualiModel getAvaliacaoProjetoQuali() {
			return avaliacaoProjetoQuali;
		}

		public void setAvaliacaoProjetoQuali(AvaliacaoProjetoQualiModel avaliacaoProjetoQuali) {
			this.avaliacaoProjetoQuali = avaliacaoProjetoQuali;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((avaliacaoProjetoPrazo == null) ? 0 : avaliacaoProjetoPrazo.hashCode());
			result = prime * result + ((avaliacaoProjetoQuali == null) ? 0 : avaliacaoProjetoQuali.hashCode());
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
			RelAvaliacaoProjetoModelPK other = (RelAvaliacaoProjetoModelPK) obj;
			if (avaliacaoProjetoPrazo == null) {
				if (other.avaliacaoProjetoPrazo != null)
					return false;
			} else if (!avaliacaoProjetoPrazo.equals(other.avaliacaoProjetoPrazo))
				return false;
			if (avaliacaoProjetoQuali == null) {
				if (other.avaliacaoProjetoQuali != null)
					return false;
			} else if (!avaliacaoProjetoQuali.equals(other.avaliacaoProjetoQuali))
				return false;
			return true;
		}
	}
}
