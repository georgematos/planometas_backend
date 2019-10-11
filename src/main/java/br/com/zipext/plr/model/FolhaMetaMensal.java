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

@Entity
@Table(schema = "METAS", name = "FAT_FOLHA_META_MENSAL")
public class FolhaMetaMensal {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatFolhaMetaMensalSeq")
	@SequenceGenerator(schema = "METAS", name = "fatFolhaMetaMensalSeq", sequenceName = "fat_folha_meta_mensal_seq", allocationSize = 1)
	@Column(name = "CD_LANCAMENTO")
	private Long id;
	
	@Column(name = "FLG_TIPO_META")
	private String tipoMeta;
	
	@Column(name = "VAL_META")
	private BigDecimal valorMeta;
	
	@ManyToOne
	@JoinColumn(name = "CD_META")
	private MetasModel meta;
	
	@ManyToOne
	@JoinColumn(name = "SKY_TEMPO")
	private TempoModel prazo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoMeta() {
		return tipoMeta;
	}

	public void setTipoMeta(String tipoMeta) {
		this.tipoMeta = tipoMeta;
	}

	public BigDecimal getValorMeta() {
		return valorMeta;
	}

	public void setValorMeta(BigDecimal valorMeta) {
		this.valorMeta = valorMeta;
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
		FolhaMetaMensal other = (FolhaMetaMensal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
