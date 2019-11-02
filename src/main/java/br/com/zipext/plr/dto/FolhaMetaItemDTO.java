package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FolhaMetaAnualModel;
import br.com.zipext.plr.model.FolhaMetaItemModel;
import br.com.zipext.plr.model.FolhaMetaMensalModel;

public class FolhaMetaItemDTO {
	
	private Long id;
	
	private FolhaMetaDTO folhaMeta;
	
	private Integer sequencia;
	
	private BigDecimal peso;
	
	private BigDecimal desempenho;
	
	private MetasDTO meta;
	
	private List<ViewFolhaMetaMensalDTO> viewMetasMensais;
	
	public FolhaMetaItemDTO() {}
	
	public FolhaMetaItemDTO(FolhaMetaItemModel model, FolhaMetaAnualModel folhaMetaAnual, List<FolhaMetaMensalModel> folhaMetasMensais, boolean isPerfilReadOnly) {
		BeanUtils.copyProperties(model, this);
		this.meta = new MetasDTO(model.getMeta());
		this.folhaMeta = new FolhaMetaDTO(model.getFolhaMeta());
		if (folhaMetaAnual != null) {
			this.desempenho = folhaMetaAnual.getDesempenho();			
		}
		
		this.viewMetasMensais = new ArrayList<>();
		if (folhaMetaAnual != null) {
			this.viewMetasMensais.add(new ViewFolhaMetaMensalDTO("REAL", folhaMetaAnual, folhaMetasMensais, isPerfilReadOnly));
			this.viewMetasMensais.add(new ViewFolhaMetaMensalDTO("META", folhaMetaAnual, folhaMetasMensais, isPerfilReadOnly));
			if (!(folhaMetaAnual.isMetaRestrita() && isPerfilReadOnly)) {
				this.viewMetasMensais.add(new ViewFolhaMetaMensalDTO("%", folhaMetaAnual, folhaMetasMensais, isPerfilReadOnly));
			}
		}
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

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public MetasDTO getMeta() {
		return meta;
	}

	public void setMeta(MetasDTO meta) {
		this.meta = meta;
	}
	
	public List<ViewFolhaMetaMensalDTO> getViewMetasMensais() {
		return viewMetasMensais;
	}

	public void setViewMetasMensais(List<ViewFolhaMetaMensalDTO> viewMetasMensais) {
		this.viewMetasMensais = viewMetasMensais;
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

	public static class ViewFolhaMetaMensalDTO {
		
		private String tipoMeta;
		private BigDecimal valJan;
		private BigDecimal valFev;
		private BigDecimal valMar;
		private BigDecimal valAbr;
		private BigDecimal valMai;
		private BigDecimal valJun;
		private BigDecimal valJul;
		private BigDecimal valAgo;
		private BigDecimal valSet;
		private BigDecimal valOut;
		private BigDecimal valNov;
		private BigDecimal valDez;
		private BigDecimal sumMeta;
		
		public ViewFolhaMetaMensalDTO() {}
		
		public ViewFolhaMetaMensalDTO(String tipoMeta, FolhaMetaAnualModel folhaMetaAnual, List<FolhaMetaMensalModel> folhaMetasMensais, boolean isPerfilReadOnly) {
			this.tipoMeta = tipoMeta;
			this.pivotListMensaisToObject(folhaMetaAnual, folhaMetasMensais, isPerfilReadOnly);
		}
		
		public void pivotListMensaisToObject(FolhaMetaAnualModel folhaMetaAnual, List<FolhaMetaMensalModel> folhaMetasMensais, boolean isPerfilReadOnly) {
			boolean isMetaRestrita = folhaMetaAnual.isMetaRestrita() && isPerfilReadOnly;
			FolhaMetaMensalModel fmJan = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(1)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmFev = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(2)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmMar = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(3)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmAbr = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(4)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmMai = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(5)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmJun = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(6)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmJul = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(7)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmAgo = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(8)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmSet = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(9)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmOut = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(10)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmNov = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(11)).findFirst().orElse(new FolhaMetaMensalModel());
			FolhaMetaMensalModel fmDez = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(12)).findFirst().orElse(new FolhaMetaMensalModel());
			
			if (tipoMeta.equalsIgnoreCase("REAL")) {
				this.valJan = isMetaRestrita ? fmJan.getValorPorcentagem() : fmJan.getValorReal();
				this.valFev = isMetaRestrita ? fmFev.getValorPorcentagem() : fmFev.getValorReal();
				this.valMar = isMetaRestrita ? fmMar.getValorPorcentagem() : fmMar.getValorReal();
				this.valAbr = isMetaRestrita ? fmAbr.getValorPorcentagem() : fmAbr.getValorReal();
				this.valMai = isMetaRestrita ? fmMai.getValorPorcentagem() : fmMai.getValorReal();
				this.valJun = isMetaRestrita ? fmJun.getValorPorcentagem() : fmJun.getValorReal();
				this.valJul = isMetaRestrita ? fmJul.getValorPorcentagem() : fmJul.getValorReal();
				this.valAgo = isMetaRestrita ? fmAgo.getValorPorcentagem() : fmAgo.getValorReal();
				this.valSet = isMetaRestrita ? fmSet.getValorPorcentagem() : fmSet.getValorReal();
				this.valOut = isMetaRestrita ? fmOut.getValorPorcentagem() : fmOut.getValorReal();
				this.valNov = isMetaRestrita ? fmNov.getValorPorcentagem() : fmNov.getValorReal();
				this.valDez = isMetaRestrita ? fmDez.getValorPorcentagem() : fmDez.getValorReal();
				
				this.sumMeta = isMetaRestrita ? folhaMetaAnual.getValorPercentAtingido() : folhaMetaAnual.getValorRealizado();
			} else if (tipoMeta.equalsIgnoreCase("%")) {
				this.valJan = fmJan.getValorPorcentagem();
				this.valFev = fmFev.getValorPorcentagem();
				this.valMar = fmMar.getValorPorcentagem();
				this.valAbr = fmAbr.getValorPorcentagem();
				this.valMai = fmMai.getValorPorcentagem();
				this.valJun = fmJun.getValorPorcentagem();
				this.valJul = fmJul.getValorPorcentagem();
				this.valAgo = fmAgo.getValorPorcentagem();
				this.valSet = fmSet.getValorPorcentagem();
				this.valOut = fmOut.getValorPorcentagem();
				this.valNov = fmNov.getValorPorcentagem();
				this.valDez = fmDez.getValorPorcentagem();
				
				this.sumMeta = folhaMetaAnual.getValorPercentAtingido();
			} else {
				this.valJan = isMetaRestrita ? new BigDecimal(100) : fmJan.getValorMeta();
				this.valFev = isMetaRestrita ? new BigDecimal(100) : fmFev.getValorMeta();
				this.valMar = isMetaRestrita ? new BigDecimal(100) : fmMar.getValorMeta();
				this.valAbr = isMetaRestrita ? new BigDecimal(100) : fmAbr.getValorMeta();
				this.valMai = isMetaRestrita ? new BigDecimal(100) : fmMai.getValorMeta();
				this.valJun = isMetaRestrita ? new BigDecimal(100) : fmJun.getValorMeta();
				this.valJul = isMetaRestrita ? new BigDecimal(100) : fmJul.getValorMeta();
				this.valAgo = isMetaRestrita ? new BigDecimal(100) : fmAgo.getValorMeta();
				this.valSet = isMetaRestrita ? new BigDecimal(100) : fmSet.getValorMeta();
				this.valOut = isMetaRestrita ? new BigDecimal(100) : fmOut.getValorMeta();
				this.valNov = isMetaRestrita ? new BigDecimal(100) : fmNov.getValorMeta();
				this.valDez = isMetaRestrita ? new BigDecimal(100) : fmDez.getValorMeta();
				
				this.sumMeta = isMetaRestrita ? new BigDecimal(100) : folhaMetaAnual.getValorMeta();
			}
			
			if (isMetaRestrita) {
				this.tipoMeta = this.tipoMeta.concat(" (%)");
			}
		}

		public String getTipoMeta() {
			return tipoMeta;
		}

		public void setTipoMeta(String tipoMeta) {
			this.tipoMeta = tipoMeta;
		}

		public BigDecimal getValJan() {
			return valJan;
		}

		public void setValJan(BigDecimal valJan) {
			this.valJan = valJan;
		}

		public BigDecimal getValFev() {
			return valFev;
		}

		public void setValFev(BigDecimal valFev) {
			this.valFev = valFev;
		}

		public BigDecimal getValMar() {
			return valMar;
		}

		public void setValMar(BigDecimal valMar) {
			this.valMar = valMar;
		}

		public BigDecimal getValAbr() {
			return valAbr;
		}

		public void setValAbr(BigDecimal valAbr) {
			this.valAbr = valAbr;
		}

		public BigDecimal getValMai() {
			return valMai;
		}

		public void setValMai(BigDecimal valMai) {
			this.valMai = valMai;
		}

		public BigDecimal getValJun() {
			return valJun;
		}

		public void setValJun(BigDecimal valJun) {
			this.valJun = valJun;
		}

		public BigDecimal getValJul() {
			return valJul;
		}

		public void setValJul(BigDecimal valJul) {
			this.valJul = valJul;
		}

		public BigDecimal getValAgo() {
			return valAgo;
		}

		public void setValAgo(BigDecimal valAgo) {
			this.valAgo = valAgo;
		}

		public BigDecimal getValSet() {
			return valSet;
		}

		public void setValSet(BigDecimal valSet) {
			this.valSet = valSet;
		}

		public BigDecimal getValOut() {
			return valOut;
		}

		public void setValOut(BigDecimal valOut) {
			this.valOut = valOut;
		}

		public BigDecimal getValNov() {
			return valNov;
		}

		public void setValNov(BigDecimal valNov) {
			this.valNov = valNov;
		}

		public BigDecimal getValDez() {
			return valDez;
		}

		public void setValDez(BigDecimal valDez) {
			this.valDez = valDez;
		}

		public BigDecimal getSumMeta() {
			return sumMeta;
		}

		public void setSumMeta(BigDecimal sumMeta) {
			this.sumMeta = sumMeta;
		}
	}
}
