package br.com.zipext.plr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "METAS" ,name = "CAD_TIPO_META")
public class TipoMetaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadTipoMetaSeq")
	@SequenceGenerator(schema = "METAS", name = "cadTipoMetaSeq", sequenceName = "cad_tipo_meta_seq", allocationSize = 1)
	@Column(name = "CD_TIPO_META")
	private Long id;
	
	@Column(name = "DS_TIPO_META")
	private String descricao;
	
	@Column(name = "DS_TIPO_META_ABV")
	private String abreviacao;
	
	public TipoMetaModel() {}
	
	public TipoMetaModel(Long id) {
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

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		TipoMetaModel other = (TipoMetaModel) obj;
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
		return true;
	}
}
