package br.com.zipext.plr.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
@Table(schema = "METAS", name = "CAD_META")
public class MetasModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadMetasSeq")
	@SequenceGenerator(schema = "METAS", name = "cadMetasSeq", sequenceName = "CAD_META_SEQ",  allocationSize = 1)
	@Column(name = "CD_META")
	private Long id;

	@Column(name = "CD_META_LEGADO")
	private Long codigoLegado;

	@Column(name = "DS_META")
	private String descricao;
	
	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	@Column(name = "FL_ATIVO")
	private String situacao;
	
	@Column(name = "FL_QUANTI_QUALI")
	private String isQuantitativa;
	
	@Column(name = "VAL_META")
	private BigDecimal valor;
	
	@Column(name = "DT_INC")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime inclusao;
    
    @Column(name = "CD_LOGIN_INC")
    private String responsavelInclusao;
    
    @ManyToOne
    @JoinColumn(name = "CD_APROVADOR")
    private ColaboradorModel aprovador;
    
    @ManyToOne
    @JoinColumn(name = "CD_META_NUMERADOR")
    private MetasModel metaNumerador;
    
    @ManyToOne
    @JoinColumn(name = "CD_META_DENOMINADOR")
    private MetasModel metaDenominador;

	@ManyToOne
	@JoinColumn(name = "SKY_PRAZO")
	private TempoModel prazo;
	
	@ManyToOne
	@JoinColumn(name = "CD_FORMULA")
	private FormulaModel formula;
	
	@ManyToOne
	@JoinColumn(name = "CD_FREQ_MEDICAO")
	private FrequenciaMedicaoModel frequenciaMedicao;
	
	@ManyToOne
	@JoinColumn(name = "CD_TIPO_MEDICAO")
	private TipoMedicaoModel tipoMedicao;
	
	@ManyToOne
	@JoinColumn(name = "CD_TIPO_META")
	private TipoMetaModel tipoMeta;
	
	@OneToMany(mappedBy = "pk.metas")
	private List<MetasPeriodoModel> metasPeriodo;
	
	@OneToMany(mappedBy = "meta")
	private List<FolhaMetaMensalModel> folhaMetasMensais;
	
	public MetasModel() {}
	
	public MetasModel(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getCodigoLegado() {
		return codigoLegado;
	}

	public void setCodigoLegado(Long codigoLegado) {
		this.codigoLegado = codigoLegado;
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

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
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

	public FrequenciaMedicaoModel getFrequenciaMedicao() {
		return frequenciaMedicao;
	}

	public void setFrequenciaMedicao(FrequenciaMedicaoModel frequenciaMedicao) {
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
	
	public List<MetasPeriodoModel> getMetasPeriodo() {
		return metasPeriodo;
	}

	public void setMetasPeriodo(List<MetasPeriodoModel> metasPeriodo) {
		this.metasPeriodo = metasPeriodo;
	}
	
	public ColaboradorModel getAprovador() {
		return aprovador;
	}

	public void setAprovador(ColaboradorModel aprovador) {
		this.aprovador = aprovador;
	}

	public MetasModel getMetaNumerador() {
		return metaNumerador;
	}

	public void setMetaNumerador(MetasModel metaNumerador) {
		this.metaNumerador = metaNumerador;
	}

	public MetasModel getMetaDenominador() {
		return metaDenominador;
	}

	public void setMetaDenominador(MetasModel metaDenominador) {
		this.metaDenominador = metaDenominador;
	}
	
	public List<FolhaMetaMensalModel> getFolhaMetasMensais() {
		return folhaMetasMensais;
	}

	public void setFolhaMetasMensais(List<FolhaMetaMensalModel> folhaMetasMensais) {
		this.folhaMetasMensais = folhaMetasMensais;
	}

	/*Export*/
	
	public String isQuantitativoQualitativo() {
		return this.isQuantitativa.equals("1") ? "QUANTITATIVA" : "QUALITATIVA";
	}
	
	public String getPeriodosAtivos() {
		if (this.metasPeriodo != null && !this.metasPeriodo.isEmpty()) {
			return
					this.metasPeriodo.stream().map(p -> p.getPk().getTempo().getAno().toString()).collect(Collectors.joining(";"));
		}
		
		return "-";
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
