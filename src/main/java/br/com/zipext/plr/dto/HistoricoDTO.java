package br.com.zipext.plr.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.enums.EnumSituacao;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.HistoricoModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.utils.PLRUtils;

public class HistoricoDTO {

	private Long id;
	
	private Long versao;
	
	private Character situacao;
	
	private String descSituacao;
	
	private String matricula;
	
	private String nomeColaborador;

	private String matriculaResponsavel;
	
	private String comentario;
	
	private String dataInclusao;
	
	private String inicioVigencia;
	
	private String fimVigencia;

	private String base64Img;
	
	private UsuarioDTO responsavel;
	
	public HistoricoDTO() {}
	
	public HistoricoDTO(HistoricoModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			this.matricula = model.getColaborador().getMatricula();
			this.nomeColaborador = model.getColaborador().getNome();
			this.responsavel = new UsuarioDTO(model.getResponsavel().getLogin(), model.getResponsavel().getNome());
			this.dataInclusao = model.getDataInclusao().toLocalDate().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS));
			this.inicioVigencia = model.getInicioVigencia().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS));
			this.fimVigencia = model.getFimVigencia().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS));
			this.descSituacao = EnumSituacao.forValue(this.situacao.toString()).getDescricao();
			this.base64Img = model.getBase64Img();
		}
	}
	
	public HistoricoModel obterModelFromDTO() {
		HistoricoModel model = new HistoricoModel();
		
		BeanUtils.copyProperties(this, model);
		model.setColaborador(new ColaboradorModel(this.matricula));
		model.setResponsavel(new UsuarioModel(this.matriculaResponsavel));
		model.setDataInclusao(LocalDateTime.now());
		model.setInicioVigencia(LocalDate.parse(this.inicioVigencia.substring(0, 10), DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_DB)));
		model.setFimVigencia(LocalDate.parse(this.fimVigencia.substring(0, 10), DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_DB)));
		
		return
				model;
	}
	
	public HistoricoModel obterModelFromDTOSimples() {
		HistoricoModel model = new HistoricoModel();
		
		BeanUtils.copyProperties(this, model);
		model.setColaborador(new ColaboradorModel(this.matricula));
		model.setResponsavel(new UsuarioModel(this.matriculaResponsavel));
		
		return
				model;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public UsuarioDTO getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(UsuarioDTO responsavel) {
		this.responsavel = responsavel;
	}

	public String getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getMatriculaResponsavel() {
		return matriculaResponsavel;
	}

	public void setMatriculaResponsavel(String matriculaResponsavel) {
		this.matriculaResponsavel = matriculaResponsavel;
	}

	public String getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(String inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public String getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(String fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public String getDescSituacao() {
		return descSituacao;
	}

	public void setDescSituacao(String descSituacao) {
		this.descSituacao = descSituacao;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public String getBase64Img() {
		return base64Img;
	}

	public void setBase64Img(String base64Img) {
		this.base64Img = base64Img;
	}
}
