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
@Table(name = "ASS_COLAB_META_GERAL", schema = "BET_PLR")
public class ColaboradorMetaGeralModel {
	
	@EmbeddedId
	private ColaboradorMetaGeralModelPK pk;

	@Column(name = "VAL_BONUS")
	private BigDecimal bonus;
	
	@Column(name = "VAL_META")
	private BigDecimal valor;
	
	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	public ColaboradorMetaGeralModel() {}
	
	public ColaboradorMetaGeralModel(ColaboradorModel colaborador, MetaGeralModel metaGeral) {
		this.pk = new ColaboradorMetaGeralModelPK(colaborador, metaGeral);
	}
	
	@Embeddable
	public static class ColaboradorMetaGeralModelPK implements Serializable {
		
		private static final long serialVersionUID = -5672038148264408535L;

		@ManyToOne
		@JoinColumn(name="CD_MATRICULA")
		private ColaboradorModel colaborador;
		
		@ManyToOne
		@JoinColumn(name = "CD_META")
		private MetaGeralModel metaGeral;
		
		public ColaboradorMetaGeralModelPK() {}
		
		public ColaboradorMetaGeralModelPK(ColaboradorModel colaboradorCargo, MetaGeralModel metaGeral) {
			this.colaborador = colaboradorCargo;
			this.metaGeral = metaGeral;
		}

		public ColaboradorModel getColaborador() {
			return colaborador;
		}

		public void setColaborador(ColaboradorModel colaborador) {
			this.colaborador = colaborador;
		}

		public MetaGeralModel getMetaGeral() {
			return metaGeral;
		}

		public void setMetaGeral(MetaGeralModel metaGeral) {
			this.metaGeral = metaGeral;
		}
	}

	public ColaboradorMetaGeralModelPK getPk() {
		return pk;
	}

	public void setPk(ColaboradorMetaGeralModelPK pk) {
		this.pk = pk;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
