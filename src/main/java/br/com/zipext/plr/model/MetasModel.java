package br.com.zipext.plr.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.zipext.plr.enums.EnumSituacao;

@Entity
@Table(schema = "METAS", name = "CAD_META")
public class MetasModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadMetasSeq")
	@SequenceGenerator(schema = "METAS", name = "cadMetasSeq", sequenceName = "CAD_META_SEQ",  allocationSize = 1)
	@Column(name = "CD_META")
	public Long id;

	@Column(name = "DS_META")
	public String descricao;
	
	@Column(name = "DS_OBSERVACAO")
	public String observacao;
	
	@Column(name = "FL_ATIVO")
	public EnumSituacao situacao;
	
	@Column(name = "FL_QUANTI_QUALI")
	public String isQuantitativa;
	
	@Column(name = "VAL_META")
	public BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "SKY_PRAZO")
	public TempoModel prazo;
	
	@ManyToOne
	@JoinColumn(name = "CD_FORMULA")
	public FormulaModel formula;
	
	@ManyToOne
	@JoinColumn(name = "CD_FREQ_MEDICAO")
	public FrequenciaMedicao frequenciaMedicao;
	
	@ManyToOne
	@JoinColumn(name = "CD_TIPO_MEDICAO")
	public TipoMedicaoModel tipoMedicao;
	
	@ManyToOne
	@JoinColumn(name = "CD_TIPO_META")
	public TipoMetaModel tipoMeta;
	
	public MetasModel() {}
	
	public MetasModel(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public EnumSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumSituacao situacao) {
		this.situacao = situacao;
	}

	public String getIsQuantitativa() {
		return isQuantitativa;
	}

	public void setIsQuantitativa(String isQuantitativa) {
		this.isQuantitativa = isQuantitativa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public TempoModel getPrazo() {
		return prazo;
	}

	public void setPrazo(TempoModel prazo) {
		this.prazo = prazo;
	}
	
	public FormulaModel getFormula() {
		return formula;
	}

	public void setFormula(FormulaModel formula) {
		this.formula = formula;
	}

	public FrequenciaMedicao getFrequenciaMedicao() {
		return frequenciaMedicao;
	}

	public void setFrequenciaMedicao(FrequenciaMedicao frequenciaMedicao) {
		this.frequenciaMedicao = frequenciaMedicao;
	}

	public TipoMedicaoModel getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(TipoMedicaoModel tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public TipoMetaModel getTipoMeta() {
		return tipoMeta;
	}

	public void setTipoMeta(TipoMetaModel tipoMeta) {
		this.tipoMeta = tipoMeta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MetasModel other = (MetasModel) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}
}
