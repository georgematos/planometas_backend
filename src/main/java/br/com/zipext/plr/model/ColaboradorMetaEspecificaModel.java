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

@Entity
@Table(name = "ASS_COLAB_META_ESPECIF", schema = "BET_PLR")
public class ColaboradorMetaEspecificaModel {
	
	@EmbeddedId
	private ColaboradorMetaEspecificaModelPK pk;
	
	@Column(name = "DS_META")
	private String meta;

	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	@Column(name = "DS_FREQUENCIA_MED")
	private String frequenciaMedicao;
	
	@Column(name = "DS_DESCRICAO")
	private String descricao;

	@Column(name = "QT_PESO")
	private BigDecimal peso;

	@Column(name = "DT_PRAZO")
	private LocalDate prazo;
	
	@Column(name = "DT_INC")
	private LocalDateTime dataInclusao;
	
	@Column(name = "CD_LOGIN_INC")
	private String responsavel;
	
	public ColaboradorMetaEspecificaModel() {}
		
	public ColaboradorMetaEspecificaModel(ColaboradorModel colaboradorModel, MetaEspecificaModel metaEspecifica, Integer sequencia) {
		this.pk = new ColaboradorMetaEspecificaModelPK(colaboradorModel, metaEspecifica, sequencia);
	}

	@Embeddable
	public static class ColaboradorMetaEspecificaModelPK implements Serializable {

		private static final long serialVersionUID = -4605842892837620440L;
		
		@ManyToOne
		@JoinColumn(name="CD_MATRICULA")
		private ColaboradorModel colaborador;
		
		@ManyToOne
		@JoinColumn(name = "CD_META")
		private MetaEspecificaModel metaEspecifica;
		
		@Column(name = "NU_SEQUENCIA")
		private Integer sequencia;
		
		public ColaboradorMetaEspecificaModelPK() {}
		
		public ColaboradorMetaEspecificaModelPK(ColaboradorModel colaborador, MetaEspecificaModel metaEspecifica, Integer sequencia) {
			this.sequencia = sequencia;
			this.colaborador = colaborador;
			this.metaEspecifica = metaEspecifica;
		}

		public ColaboradorModel getColaborador() {
			return colaborador;
		}

		public void setColaborador(ColaboradorModel colaborador) {
			this.colaborador = colaborador;
		}

		public MetaEspecificaModel getMetaEspecifica() {
			return metaEspecifica;
		}

		public void setMetaEspecifica(MetaEspecificaModel metaEspecifica) {
			this.metaEspecifica = metaEspecifica;
		}

		public Integer getSequencia() {
			return sequencia;
		}

		public void setSequencia(Integer sequencia) {
			this.sequencia = sequencia;
		}
	}

	public ColaboradorMetaEspecificaModelPK getPk() {
		return pk;
	}

	public void setPk(ColaboradorMetaEspecificaModelPK pk) {
		this.pk = pk;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getFrequenciaMedicao() {
		return frequenciaMedicao;
	}

	public void setFrequenciaMedicao(String frequenciaMedicao) {
		this.frequenciaMedicao = frequenciaMedicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}
