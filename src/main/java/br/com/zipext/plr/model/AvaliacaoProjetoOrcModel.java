package br.com.zipext.plr.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.zipext.plr.converter.LocalDateTimeConverter;

@Entity
@Table(schema = "METAS", name = "CAD_AVAL_META_REL_ORC")
public class AvaliacaoProjetoOrcModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAvalMetaOrc")
	@SequenceGenerator(schema = "METAS", name = "seqAvalMetaOrc", sequenceName = "CD_AVAL_META_ORC_SEQ", initialValue = 1000)
	@Column(name = "CD_AVAL_META_ORC")
	private Long id;
	
	@Column(name = "DS_AVAL_META_ORC")
	private String descricao;
	
	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;
    
    @Column(name = "DT_ALT")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime alteracao;
    
    @Column(name = "CD_LOGIN_ALT")
    private String responsavelAlteracao;
    
    @OneToMany(mappedBy = "pk.avaliacaoProjetoOrc")
    private List<RelAvaliacaoProjetoModel> avaliacoesProjeto;
    
    public AvaliacaoProjetoOrcModel() {}
    
    public AvaliacaoProjetoOrcModel(Long id) {
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

	public LocalDateTime getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(LocalDateTime alteracao) {
		this.alteracao = alteracao;
	}

	public String getResponsavelAlteracao() {
		return responsavelAlteracao;
	}

	public void setResponsavelAlteracao(String responsavelAlteracao) {
		this.responsavelAlteracao = responsavelAlteracao;
	}
	
	public List<RelAvaliacaoProjetoModel> getAvaliacoesProjeto() {
		return avaliacoesProjeto;
	}

	public void setAvaliacoesProjeto(List<RelAvaliacaoProjetoModel> avaliacoesProjeto) {
		this.avaliacoesProjeto = avaliacoesProjeto;
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
		AvaliacaoProjetoOrcModel other = (AvaliacaoProjetoOrcModel) obj;
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
