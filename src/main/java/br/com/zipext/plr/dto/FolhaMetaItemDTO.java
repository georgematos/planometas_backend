package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FolhaMetaAnualModel;
import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaMensalModel;
import br.com.zipext.plr.model.FolhaMetaModel;
import br.com.zipext.plr.model.MetasModel;

public class FolhaMetaItemDTO {
	
	private Long id;
	
	private FolhaMetaDTO folhaMeta;
	
	private Integer sequencia;
	
	private BigDecimal peso;
	
	private BigDecimal desempenho;
	
	private BigDecimal pontuacao;
	
	private MetasDTO meta;
	
	private List<FolhaMetaMensalDTO> viewMetasMensais;
	
	public FolhaMetaItemDTO() {}
	
	public FolhaMetaItemDTO(FolhaMetaItemModel model) {
		BeanUtils.copyProperties(model, this);
		this.meta = new MetasDTO(model.getMeta());
	}
	
	public FolhaMetaItemDTO(FolhaMetaItemModel model, FolhaMetaAnualModel folhaMetaAnual, List<FolhaMetaMensalModel> folhaMetasMensais, boolean isPerfilReadOnly) {
		BeanUtils.copyProperties(model, this);
		this.meta = new MetasDTO(model.getMeta());
		this.folhaMeta = new FolhaMetaDTO(model.getFolhaMeta());
		if (folhaMetaAnual != null) {
			this.desempenho = folhaMetaAnual.getDesempenho();
			this.pontuacao = this.desempenho.multiply(model.getPeso()).divide(new BigDecimal(100), 4, RoundingMode.CEILING);
		}
		
		this.viewMetasMensais = new ArrayList<>();
		if (folhaMetaAnual != null) {
			this.viewMetasMensais.add(new FolhaMetaMensalDTO("META", folhaMetaAnual, folhaMetasMensais, isPerfilReadOnly));
			this.viewMetasMensais.add(new FolhaMetaMensalDTO("REAL", folhaMetaAnual, folhaMetasMensais, isPerfilReadOnly));
			if (!(folhaMetaAnual.isMetaRestrita() && isPerfilReadOnly)) {
				this.viewMetasMensais.add(new FolhaMetaMensalDTO("%", folhaMetaAnual, folhaMetasMensais, isPerfilReadOnly));
			}
		}
	}
	
	public FolhaMetaItemModel obterModel() {
		FolhaMetaItemModel model = new FolhaMetaItemModel();
		
		model.setPeso(this.peso);
		model.setSequencia(this.sequencia);
		model.setMeta(new MetasModel(this.meta.getId()));
		model.setFolhaMeta(new FolhaMetaModel(this.folhaMeta.getId()));
		model.setInclusao(LocalDateTime.now());
		model.setResponsavelInclusao("SISTEMA");
		
		return model;
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
	
	public BigDecimal getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(BigDecimal pontuacao) {
		this.pontuacao = pontuacao;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public MetasDTO getMeta() {
		return meta;
	}

	public void setMeta(MetasDTO meta) {
		this.meta = meta;
	}
	
	public BigDecimal getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(BigDecimal desempenho) {
		this.desempenho = desempenho;
	}
	
	public FolhaMetaDTO getFolhaMeta() {
		return folhaMeta;
	}

	public void setFolhaMeta(FolhaMetaDTO folhaMeta) {
		this.folhaMeta = folhaMeta;
	}

	public List<FolhaMetaMensalDTO> getViewMetasMensais() {
		return viewMetasMensais;
	}

	public void setViewMetasMensais(List<FolhaMetaMensalDTO> viewMetasMensais) {
		this.viewMetasMensais = viewMetasMensais;
	}
}
