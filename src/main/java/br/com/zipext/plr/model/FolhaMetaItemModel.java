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
@Table(schema = "FAT", name = "FAT_FOLHA_META_ITEM")
public class FolhaMetaItemModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatFolhaMetaItemSeq")
	@SequenceGenerator(schema = "FAT", name = "fatFolhaMetaItemSeq", sequenceName = "fat_folha_meta_item_seq", allocationSize = 1)
	@Column(name = "CD_LANCAMENTO")
	private Long id;
	
	@Column(name = "NU_SEQUENCIA")
	private Integer sequencia;
	
	@Column(name = "VAL_PESO")
	private BigDecimal peso;
	
	@Column(name = "FL_ITEM_SUGERIDO")
	private String tipoSugerida;
	
	@ManyToOne
	@JoinColumn(name = "CD_META")
	private MetasModel meta;
	
	@ManyToOne
	@JoinColumn(name = "CD_FOLHA_META")
	private FolhaMetaModel folhaMeta;
	
	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;
	
	public FolhaMetaItemModel() {}
	
	public FolhaMetaItemModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public MetasModel getMeta() {
		return meta;
	}

	public void setMeta(MetasModel meta) {
		this.meta = meta;
	}

	public FolhaMetaModel getFolhaMeta() {
		return folhaMeta;
	}

	public void setFolhaMeta(FolhaMetaModel folhaMeta) {
		this.folhaMeta = folhaMeta;
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

	public String getTipoSugerida() {
		return tipoSugerida;
	}

	public void setTipoSugerida(String tipoSugerida) {
		this.tipoSugerida = tipoSugerida;
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
		FolhaMetaItemModel other = (FolhaMetaItemModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
