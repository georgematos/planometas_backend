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

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@Entity
@Table(name = "ASS_COLAB_META_ESPECIF", schema = "BET_PLR")
public class ColaboradorMetaEspecificaModel {
	
	@EmbeddedId
	private ColaboradorMetaEspecificaModelPK pk;
	
	@Column(name = "NU_SEQUENCIA")
	private Integer sequencia;
	
	@Column(name = "DS_META")
	private String meta;

	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	@Column(name = "DS_FREQUENCIA")
	private String frequenciaMedicao;

	@Column(name = "QT_PESO")
	private BigDecimal peso;

	@Column(name = "DT_PRAZO")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime prazo;
	
	public ColaboradorMetaEspecificaModel() {}
		
	public ColaboradorMetaEspecificaModel(ColaboradorModel colaboradorModel, MetaEspecificaModel metaEspecifica) {
		this.pk = new ColaboradorMetaEspecificaModelPK(colaboradorModel, metaEspecifica);
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
		
		public ColaboradorMetaEspecificaModelPK() {}
		
		public ColaboradorMetaEspecificaModelPK(ColaboradorModel colaborador, MetaEspecificaModel metaEspecifica) {
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
	}

	public ColaboradorMetaEspecificaModelPK getPk() {
		return pk;
	}

	public void setPk(ColaboradorMetaEspecificaModelPK pk) {
		this.pk = pk;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
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

	public LocalDateTime getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDateTime prazo) {
		this.prazo = prazo;
	}

	public String getFrequenciaMedicao() {
		return frequenciaMedicao;
	}

	public void setFrequenciaMedicao(String frequenciaMedicao) {
		this.frequenciaMedicao = frequenciaMedicao;
	}
}
