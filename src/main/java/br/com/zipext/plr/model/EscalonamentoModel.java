package br.com.zipext.plr.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.zipext.plr.converter.LocalDateTimeConverter;

@Entity
@Table(schema = "METAS", name = "CAD_ESCALONAMENTO")
public class EscalonamentoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadEscalonamentoSeq")
	@SequenceGenerator(schema = "METAS", name = "cadEscalonamentoSeq", sequenceName = "cad_escalonamento_seq", allocationSize = 1)
	@Column(name = "CD_ESCALONAMENTO")
	private Long id;

	@Column(name = "VAL_FAIXA")
	private BigDecimal faixa;

	@Column(name = "VAL_DESEMPENHO")
	private BigDecimal desempenho;

	@ManyToOne
	@JoinColumn(name = "CD_TIPO_MEDICAO")
	private TipoMedicaoModel tipoMedicao;

	@Column(name = "CD_LOGIN_INC")
	private String responsavelInclusao;

	@Column(name = "CD_LOGIN_ALT")
	private String responsavelAlteracao;

	@Column(name = "DT_INC")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime inclusao;

	@Column(name = "DT_ALT")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime alteracao;

	public EscalonamentoModel() {
	}

	public EscalonamentoModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getFaixa() {
		return faixa;
	}

	public void setFaixa(BigDecimal faixa) {
		this.faixa = faixa;
	}

	public BigDecimal getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(BigDecimal desempenho) {
		this.desempenho = desempenho;
	}

	public TipoMedicaoModel getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(TipoMedicaoModel tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getResponsavelInclusao() {
		return responsavelInclusao;
	}

	public void setResponsavelInclusao(String responsavelInclusao) {
		this.responsavelInclusao = responsavelInclusao;
	}

	public String getResponsavelAlteracao() {
		return responsavelAlteracao;
	}

	public void setResponsavelAlteracao(String responsavelAlteracao) {
		this.responsavelAlteracao = responsavelAlteracao;
	}

	public LocalDateTime getInclusao() {
		return inclusao;
	}

	public void setInclusao(LocalDateTime inclusao) {
		this.inclusao = inclusao;
	}

	public LocalDateTime getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(LocalDateTime alteracao) {
		this.alteracao = alteracao;
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
		EscalonamentoModel other = (EscalonamentoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
