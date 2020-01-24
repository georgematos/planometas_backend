package br.com.zipext.plr.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.utils.PLRUtils;

public class FolhaMetaDTO {
	
	private Long id;
	
	private String situacao;
	
	private String inicioVigencia;

	private String fimVigencia;
	
	private String dataCadastro;
	
	private ColaboradorDTO colaborador;
	
	private ColaboradorDTO responsavel;
	
	private List<FolhaMetaItemDTO> folhasMetaItem;
	
	private List<FolhaMetaMensalDTO> folhaMetasMensais;
	
	public FolhaMetaDTO() {}
	
	public FolhaMetaDTO(FolhaMetaModel model) {
		BeanUtils.copyProperties(model, this);
		
		this.colaborador = new ColaboradorDTO(model.getColaborador());
		this.dataCadastro = model.getInclusao() != null ? model.getInclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
		this.responsavel = new ColaboradorDTO(model.getResponsavel());	
		this.inicioVigencia = model.getInicioVigencia().getDescricao();
		this.fimVigencia = model.getFimVigencia().getDescricao();
		this.situacao = model.getSituacao();
		this.folhasMetaItem = model.getFolhaMetaItems().stream().map(FolhaMetaItemDTO::new).collect(Collectors.toList());
		this.folhaMetasMensais = new ArrayList<>();
		
		model.getFolhaMetaItems().stream()
								 .forEach(item -> this.folhaMetasMensais.add(new FolhaMetaMensalDTO("META", null, item.getMeta(), item.getMeta().getFolhaMetasMensais(), false)));
	}
	
	public FolhaMetaModel obterModel() {
		FolhaMetaModel model = new FolhaMetaModel();
		BeanUtils.copyProperties(this, model);
		
		if (StringUtils.isNotBlank(this.inicioVigencia)) {
			model.setInicioVigencia(new TempoModel(PLRUtils.getSkyTempoFromStringDate(this.inicioVigencia)));
		}
		
		if (StringUtils.isNoneBlank(this.fimVigencia)) {
			model.setFimVigencia(new TempoModel(PLRUtils.getSkyTempoFromStringDate(this.fimVigencia)));
		}
		
		if (this.colaborador != null) {
			model.setColaborador(new ColaboradorModel(this.colaborador.getMatricula()));
		}
		
		if (this.responsavel != null) {
			model.setResponsavel(new ColaboradorModel(this.responsavel.getMatricula()));
		}
		
		List<FolhaMetaItemModel> folhaMetaItems = new ArrayList<>();
		this.folhasMetaItem.forEach(fmi -> {
			FolhaMetaItemModel item = fmi.obterModel();
			item.setFolhaMeta(model);
			folhaMetaItems.add(fmi.obterModel());
		});
		
		model.setInclusao(LocalDateTime.now());
		model.setResponsavelInclusao("SISTEMA");
		
		return
				model;
	}
	
	public List<FolhaMetaItemModel> obterFolhaMetaItems(FolhaMetaModel model) {
		List<FolhaMetaItemModel> folhaMetaItems = new ArrayList<>();
		this.folhasMetaItem.forEach(fmi -> {
			FolhaMetaItemModel item = fmi.obterModel();
			item.setFolhaMeta(model);
			folhaMetaItems.add(item);
		});
		
		return
				folhaMetaItems;
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
	
	public List<FolhaMetaMensalDTO> getFolhaMetasMensais() {
		return folhaMetasMensais;
	}

	public void setFolhaMetasMensais(List<FolhaMetaMensalDTO> folhaMetasMensais) {
		this.folhaMetasMensais = folhaMetasMensais;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
