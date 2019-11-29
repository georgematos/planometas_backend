package br.com.zipext.plr.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.zipext.plr.converter.LocalDateTimeConverter;

@Entity
@Table(schema = "METAS", name = "CAD_FOLHA_META")
public class FolhaMetaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadFolhaMetaSeq")
	@SequenceGenerator(schema = "METAS", name = "cadFolhaMetaSeq", sequenceName = "cad_folha_meta_seq", allocationSize = 1)
	@Column(name = "CD_FOLHA_META")
	private Long id;
	
	@Column(name = "FL_ATIVO")
	private String situacao;
	
	@ManyToOne
	@JoinColumn(name = "CD_MATRICULA")
	private ColaboradorModel colaborador;
	
	@ManyToOne
	@JoinColumn(name = "SKY_INI_VIGENCIA")
	private TempoModel inicioVigencia;

	@ManyToOne
	@JoinColumn(name = "SKY_FIM_VIGENCIA")
	private TempoModel fimVigencia;
	
	@ManyToOne
	@JoinColumn(name = "CD_RESPONSAVEL_CADASTRO")
	private ColaboradorModel responsavel;
	
	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;
	
	@OneToMany(mappedBy = "folhaMeta", cascade = CascadeType.ALL)
	private List<FolhaMetaItemModel> folhaMetaItems;
	
	public FolhaMetaModel() {}
	
	public FolhaMetaModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public ColaboradorModel getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorModel colaborador) {
		this.colaborador = colaborador;
	}

	public TempoModel getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(TempoModel inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public TempoModel getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(TempoModel fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public ColaboradorModel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ColaboradorModel responsavel) {
		this.responsavel = responsavel;
	}

	public List<FolhaMetaItemModel> getFolhaMetaItems() {
		return folhaMetaItems;
	}

	public void setFolhaMetaItems(List<FolhaMetaItemModel> folhaMetaItems) {
		this.folhaMetaItems = folhaMetaItems;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		FolhaMetaModel other = (FolhaMetaModel) obj;
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
