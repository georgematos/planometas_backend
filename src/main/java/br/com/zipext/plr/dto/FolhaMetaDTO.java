package br.com.zipext.plr.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FolhaMetaModel;

public class FolhaMetaDTO {
	
	private Long id;
	
	private String situacao;
	
	private String inicioVigencia;

	private String fimVigencia;
	
	private ColaboradorDTO colaborador;
	
	private ColaboradorDTO responsavel;
	
	private List<FolhaMetaItemDTO> folhasMetaItem;
	
	public FolhaMetaDTO() {}
	
	public FolhaMetaDTO(FolhaMetaModel model) {
		BeanUtils.copyProperties(model, this);
		
		this.colaborador = new ColaboradorDTO(model.getColaborador());
		this.responsavel = new ColaboradorDTO(model.getResponsavel());	
		this.inicioVigencia = model.getInicioVigencia().getDescricao();
		this.fimVigencia = model.getFimVigencia().getDescricao();
		this.situacao = model.getSituacao();
		this.folhasMetaItem = model.getFolhaMetaItems().stream().map(FolhaMetaItemDTO::new).collect(Collectors.toList());
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

	public ColaboradorDTO getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorDTO colaborador) {
		this.colaborador = colaborador;
	}

	public ColaboradorDTO getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ColaboradorDTO responsavel) {
		this.responsavel = responsavel;
	}

	public List<FolhaMetaItemDTO> getFolhasMetaItem() {
		return folhasMetaItem;
	}

	public void setFolhasMetaItem(List<FolhaMetaItemDTO> folhasMetaItem) {
		this.folhasMetaItem = folhasMetaItem;
	}
}
