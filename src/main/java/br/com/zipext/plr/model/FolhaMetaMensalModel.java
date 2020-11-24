package br.com.zipext.plr.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
@Table(schema = "FAT", name = "FAT_META_MENSAL")
public class FolhaMetaMensalModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatMetaMensalSeq")
	@SequenceGenerator(schema = "FAT", name = "fatMetaMensalSeq", sequenceName = "fat_meta_mensal_seq", allocationSize = 1)
	@Column(name = "CD_LANCAMENTO")
	private Long id;
	
	@Column(name = "VAL_META")
	private BigDecimal valorMeta;
	

	@Column(name = "VAL_REAL")
	private BigDecimal valorReal;
	
	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_META")
	private MetasModel meta;
	
	@ManyToOne
	@JoinColumn(name = "SKY_TEMPO")
	private TempoModel prazo;
	
	@ManyToOne
	@JoinColumn(name = "CD_MATRICULA")
	private ColaboradorModel colaboradorMeta;
	
	
	public FolhaMetaMensalModel() {}
	
	public FolhaMetaMensalModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getValorMeta() {
		return valorMeta;
	}

	public void setValorMeta(BigDecimal valorMeta) {
		this.valorMeta = valorMeta;
	}
	
	public BigDecimal getValorReal() {
		return valorReal;
	}
	
	public BigDecimal getValorPorcentagem() {
		if (this.valorReal != null && (this.valorMeta != null && !this.valorMeta.equals(BigDecimal.ZERO))) {
			try {
				return 
						this.valorReal.divide(this.valorMeta, 4, RoundingMode.CEILING).multiply(new BigDecimal(100));
			} catch (Exception e1) {
				return 
						null;
			}
		} else if (this.valorMeta != null && !this.valorMeta.equals(BigDecimal.ZERO)) {
			return
					new BigDecimal(100);
		} else {
			return
					null;
		}
	}

	public void setValorReal(BigDecimal valorReal) {
		this.valorReal = valorReal;
	}

	public MetasModel getMeta() {
		return meta;
	}

	public void setMeta(MetasModel meta) {
		this.meta = meta;
	}

	public TempoModel getPrazo() {
		return prazo;
	}

	public void setPrazo(TempoModel prazo) {
		this.prazo = prazo;
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
	
	public ColaboradorModel getColaboradorMeta() {
		return colaboradorMeta;
	}

	public void setColaboradorMeta(ColaboradorModel colaboradorMeta) {
		this.colaboradorMeta = colaboradorMeta;
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
		FolhaMetaMensalModel other = (FolhaMetaMensalModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
