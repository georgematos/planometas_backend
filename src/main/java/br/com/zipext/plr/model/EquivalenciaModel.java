package br.com.zipext.plr.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "METAS", name = "CAD_EQUIVALENCIA")
public class EquivalenciaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadEquivalenciaSeq")
	@SequenceGenerator(schema = "METAS", name = "cadEquivalenciaSeq", sequenceName = "cad_equivalencia_seq", allocationSize = 1)
	@Column(name = "CD_EQUIVALENCIA")
	private Long id;
	
	@Column(name = "DS_EQUIVALENCIA")
	private String descricao;
	
	@Column(name = "VAL_MULTIPLICADOR")
	private BigDecimal multiplicador;
			
	@Column(name = "VAL_LIM_MULTIPLICADOR")
	private BigDecimal limiteMultiplicador;
	
	@Column(name = "VAL_LIM_SOMA_METAS")
	private BigDecimal limiteSomaMetas;
	
	@OneToMany(mappedBy = "pk.equivalencia")
	private List<MetaEquivalenciaPeriodoModel> metasEquivalencia;
	
	public EquivalenciaModel() {}
	
	public EquivalenciaModel(Long id) {
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

	public BigDecimal getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(BigDecimal multiplicador) {
		this.multiplicador = multiplicador;
	}

	public BigDecimal getLimiteMultiplicador() {
		return limiteMultiplicador;
	}

	public void setLimiteMultiplicador(BigDecimal limiteMultiplicador) {
		this.limiteMultiplicador = limiteMultiplicador;
	}
	
	public BigDecimal getLimiteSomaMetas() {
		return limiteSomaMetas;
	}

	public void setLimiteSomaMetas(BigDecimal limiteSomaMetas) {
		this.limiteSomaMetas = limiteSomaMetas;
	}

	public List<MetaEquivalenciaPeriodoModel> getMetasEquivalencia() {
		return metasEquivalencia;
	}

	public void setMetasEquivalencia(List<MetaEquivalenciaPeriodoModel> metasEquivalencia) {
		this.metasEquivalencia = metasEquivalencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EquivalenciaModel other = (EquivalenciaModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
