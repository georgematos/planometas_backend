package br.com.zipext.plr.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "BET_PLR", name = "CAD_HISTORICO_META")
public class HistoricoModel {
	
	@Id
	@SequenceGenerator(name = "cd_historic_seq", schema = "BET_PLR", sequenceName = "cad_historico_colab_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cd_historic_seq")
	@Column(name = "CD_HISTORICO")
	private Long id;
	
	@Column(name = "NU_VERSION")
	private Long versao;
	
	@Column(name = "DS_HISTORICO")
	private String comentario;
	
	@Column(name = "DT_INICIO_VIGENCIA")
	private LocalDate inicioVigencia;
	
	@Column(name = "DT_FIM_VIGENCIA")
	private LocalDate fimVigencia;
	
	@Column(name = "IN_SITUACAO_VERSAO")
	private Character situacao;
	
	@Column(name = "DT_INC")
	private LocalDateTime dataInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_LOGIN_INC")
	private UsuarioModel responsavel;
	
	@ManyToOne
	@JoinColumn(name = "CD_MATRICULA")
	private ColaboradorModel colaborador;
	
	@OneToMany(mappedBy = "pk.historico", cascade = CascadeType.ALL)
	private Set<HistoricoMetaEspecificaModel> historicoMetaEspecifica;

	@OneToMany(mappedBy = "pk.historico", cascade = CascadeType.ALL)
	private List<HistoricoMetaEspecificaMensalModel> historicoMetaEspecificaMensal;
	
	public HistoricoModel() {}
	
	public HistoricoModel(ColaboradorModel colaborador) {
		this.colaborador = colaborador;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ColaboradorModel getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorModel colaborador) {
		this.colaborador = colaborador;
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Set<HistoricoMetaEspecificaModel> getHistoricoMetaEspecifica() {
		return historicoMetaEspecifica;
	}

	public void setHistoricoMetaEspecifica(Set<HistoricoMetaEspecificaModel> historicoMetaEspecifica) {
		this.historicoMetaEspecifica = historicoMetaEspecifica;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public UsuarioModel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(UsuarioModel responsavel) {
		this.responsavel = responsavel;
	}

	public LocalDate getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(LocalDate inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public LocalDate getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(LocalDate fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public List<HistoricoMetaEspecificaMensalModel> getHistoricoMetaEspecificaMensal() {
		return historicoMetaEspecificaMensal;
	}

	public void setHistoricoMetaEspecificaMensal(List<HistoricoMetaEspecificaMensalModel> historicoMetaEspecificaMensal) {
		this.historicoMetaEspecificaMensal = historicoMetaEspecificaMensal;
	}
}
