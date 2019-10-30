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
	
	private Integer sequencia;
	
	private BigDecimal peso;
	
	private BigDecimal desempenho;
	
	private MetasDTO meta;
	
	private List<ViewFolhaMetaMensalDTO> viewMetasMensais;
	
	public FolhaMetaItemDTO() {}
	
	public FolhaMetaItemDTO(FolhaMetaItemModel model, FolhaMetaAnualModel folhaMetaAnual, List<FolhaMetaMensalModel> folhaMetasMensais) {
		BeanUtils.copyProperties(model, this);
		this.meta = new MetasDTO(model.getMeta());
		if (folhaMetaAnual != null) {
			this.desempenho = folhaMetaAnual.getDesempenho();			
		}

		this.viewMetasMensais = new ArrayList<>();
		this.viewMetasMensais.add(new ViewFolhaMetaMensalDTO("REAL", folhaMetaAnual, folhaMetasMensais));
		this.viewMetasMensais.add(new ViewFolhaMetaMensalDTO("META", folhaMetaAnual, folhaMetasMensais));
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
		
		public ViewFolhaMetaMensalDTO(String tipoMeta, FolhaMetaAnualModel folhaMetaAnual, List<FolhaMetaMensalModel> folhaMetasMensais) {
			this.tipoMeta = tipoMeta;
			this.pivotListMensaisToObject(folhaMetaAnual, folhaMetasMensais);
		}
		
		public void pivotListMensaisToObject(FolhaMetaAnualModel folhaMetaAnual, List<FolhaMetaMensalModel> folhaMetasMensais) {
			if (tipoMeta.equalsIgnoreCase("REAL")) {
				this.valJan = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(1)).findFirst().get(). getValorReal();
				this.valFev = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(2)).findFirst().get().getValorReal();
				this.valMar = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(3)).findFirst().get().getValorReal();
				this.valAbr = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(4)).findFirst().get().getValorReal();
				this.valMai = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(5)).findFirst().get().getValorReal();
				this.valJun = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(6)).findFirst().get().getValorReal();
				this.valJul = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(7)).findFirst().get().getValorReal();
				this.valAgo = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(8)).findFirst().get().getValorReal();
				this.valSet = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(9)).findFirst().get().getValorReal();
				this.valOut = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(10)).findFirst().get().getValorReal();
				this.valNov = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(11)).findFirst().get().getValorReal();
				this.valDez = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(12)).findFirst().get().getValorReal();
				this.sumMeta = folhaMetaAnual != null ? folhaMetaAnual.getValorRealizado() : BigDecimal.ZERO;
			} else {
				this.valJan = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(1)).findFirst().get().getValorMeta();
				this.valFev = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(2)).findFirst().get().getValorMeta();
				this.valMar = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(3)).findFirst().get().getValorMeta();
				this.valAbr = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(4)).findFirst().get().getValorMeta();
				this.valMai = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(5)).findFirst().get().getValorMeta();
				this.valJun = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(6)).findFirst().get().getValorMeta();
				this.valJul = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(7)).findFirst().get().getValorMeta();
				this.valAgo = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(8)).findFirst().get().getValorMeta();
				this.valSet = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(9)).findFirst().get().getValorMeta();
				this.valOut = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(10)).findFirst().get().getValorMeta();
				this.valNov = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(11)).findFirst().get().getValorMeta();
				this.valDez = folhaMetasMensais.stream().filter(fmm -> fmm.getPrazo().getMes().equals(12)).findFirst().get().getValorMeta();
				this.sumMeta = folhaMetaAnual != null ? folhaMetaAnual.getValorMeta() : BigDecimal.ZERO;
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
