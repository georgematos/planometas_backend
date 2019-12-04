package br.com.zipext.plr.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.zipext.plr.converter.LocalDateTimeConverter;


@Entity
@Table(schema = "AUTH", name = "CAD_ACESSO_INFORMACAO")
public class InformacaoAcessoModel {

	@Id
	@Column(name = "CD_INFORMACAO")
	private Long id;
	
	@Column(name = "DS_INFORMACAO")
	private String descricao;
	
	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;

    @OneToMany(mappedBy = "pk.informacaoAcesso")
    private List<PerfilInfoAcessoModel> perfisAcesso;
    
    public InformacaoAcessoModel() {}
    
    public InformacaoAcessoModel(Long id) {
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
	
	public List<PerfilInfoAcessoModel> getPerfisAcesso() {
		return perfisAcesso;
	}

	public void setPerfisAcesso(List<PerfilInfoAcessoModel> perfisAcesso) {
		this.perfisAcesso = perfisAcesso;
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
		InformacaoAcessoModel other = (InformacaoAcessoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
