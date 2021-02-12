package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.zipext.plr.enums.EnumTipoMeta;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.FolhaMetaAnualModel;
import br.com.zipext.plr.model.FolhaMetaMensalModel;
import br.com.zipext.plr.model.MetasModel;
import br.com.zipext.plr.model.TempoModel;
import br.com.zipext.plr.utils.PLRUtils;

public class FolhaMetaMensalDTO {
	
	private Long id;
	private Long idMeta;
	private BigDecimal valorMeta;
	private BigDecimal valorReal;
	private TempoDTO prazo;

	private GenericDTO aprovador;
	private GenericDTO meta;
	private GenericDTO colaboradorMeta;
	
	private String tipoMeta;
	
	private Long idJan;
	private Long idFev;
	private Long idMar;
	private Long idAbr;
	private Long idMai;
	private Long idJun;
	private Long idJul;
	private Long idAgo;
	private Long idSet;
	private Long idOut;
	private Long idNov;
	private Long idDez;
	
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

	public FolhaMetaMensalDTO() {}
	
	public FolhaMetaMensalDTO(FolhaMetaMensalModel model) {
		this.id = model.getId();
		this.idMeta = model.getMeta().getId();
	}
	
	public FolhaMetaMensalDTO(String tipoMeta, FolhaMetaAnualModel folhaMetaAnual, MetasModel meta, List<FolhaMetaMensalModel> folhaMetasMensais, boolean isPerfilReadOnly) {
		this.tipoMeta = tipoMeta;
		if (meta != null) {
			this.meta = new GenericDTO(meta);
			this.aprovador = new GenericDTO(meta.getAprovador());
			if (meta.getTipoMeta().getDescricao().equalsIgnoreCase(EnumTipoMeta.PROJETO.name())) {
				this.prazo = new TempoDTO(meta.getPrazo());
			}
		}
		
		this.pivotListMensaisToObject(folhaMetaAnual, folhaMetasMensais, isPerfilReadOnly);
	}
	
	public void pivotListMensaisToObject(FolhaMetaAnualModel folhaMetaAnual, List<FolhaMetaMensalModel> folhaMetasMensais, boolean isPerfilReadOnly) {
		boolean isMetaRestrita = folhaMetaAnual == null && !isPerfilReadOnly ? false : (folhaMetaAnual.isMetaRestrita() && isPerfilReadOnly);
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
		
		this.idJan = fmJan.getId();
		this.idFev = fmFev.getId();
		this.idMar = fmMar.getId();
		this.idAbr = fmAbr.getId();
		this.idMai = fmMai.getId();
		this.idJun = fmJun.getId();
		this.idJul = fmJul.getId();
		this.idAgo = fmAgo.getId();
		this.idSet = fmSet.getId();
		this.idOut = fmOut.getId();
		this.idNov = fmNov.getId();
		this.idDez = fmDez.getId();
		
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
			
			if (folhaMetaAnual != null) {
				this.sumMeta = isMetaRestrita ? folhaMetaAnual.getValorPercentAtingido() : folhaMetaAnual.getValorRealizado();
			}
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
			
			if (folhaMetaAnual != null) {
				this.sumMeta = folhaMetaAnual.getValorPercentAtingido();
			}
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
			
			if (folhaMetaAnual != null) {
				this.sumMeta = isMetaRestrita ? new BigDecimal(100) : folhaMetaAnual.getValorMeta();
			}
		}
		
		if (isMetaRestrita) {
			this.tipoMeta = this.tipoMeta.concat(" (%)");
		}
	}
	
	public FolhaMetaMensalModel obterModel() {
		FolhaMetaMensalModel model = new FolhaMetaMensalModel();
		
		model.setId(this.id);
		model.setMeta(new MetasModel(this.idMeta));
		model.setPrazo(this.prazo == null ? new TempoModel(-1L) : new TempoModel(this.prazo.getId()));
		model.setResponsavelInclusao(PLRUtils.SYS_USER);
		model.setInclusao(LocalDateTime.now());
		model.setValorMeta(this.valorMeta != null && this.valorMeta.equals(BigDecimal.ZERO) ? null : this.valorMeta);
		model.setValorReal(this.valorReal != null && this.valorReal.equals(BigDecimal.ZERO) ? null : this.valorReal);
		
		if (this.colaboradorMeta != null) {
			model.setColaboradorMeta(new ColaboradorModel(this.colaboradorMeta.getMatricula()));
		}

		return 
				model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getIdMeta() {
		return idMeta;
	}

	public void setIdMeta(Long idMeta) {
		this.idMeta = idMeta;
	}

	public BigDecimal getValorMeta() {
		return valorMeta;
	}

	public void setValorMeta(BigDecimal valorMeta) {
		this.valorMeta = valorMeta;
	}

	public BigDecimal getValorReal() {
		return valorReal;
	}

	public void setValorReal(BigDecimal valorReal) {
		this.valorReal = valorReal;
	}

	public TempoDTO getPrazo() {
		return prazo;
	}

	public void setPrazo(TempoDTO prazo) {
		this.prazo = prazo;
	}

	public Long getIdJan() {
		return idJan;
	}

	public void setIdJan(Long idJan) {
		this.idJan = idJan;
	}

	public Long getIdFev() {
		return idFev;
	}

	public void setIdFev(Long idFev) {
		this.idFev = idFev;
	}

	public Long getIdMar() {
		return idMar;
	}

	public void setIdMar(Long idMar) {
		this.idMar = idMar;
	}

	public Long getIdAbr() {
		return idAbr;
	}

	public void setIdAbr(Long idAbr) {
		this.idAbr = idAbr;
	}

	public Long getIdMai() {
		return idMai;
	}

	public void setIdMai(Long idMai) {
		this.idMai = idMai;
	}

	public Long getIdJun() {
		return idJun;
	}

	public void setIdJun(Long idJun) {
		this.idJun = idJun;
	}

	public Long getIdJul() {
		return idJul;
	}

	public void setIdJul(Long idJul) {
		this.idJul = idJul;
	}

	public Long getIdAgo() {
		return idAgo;
	}

	public void setIdAgo(Long idAgo) {
		this.idAgo = idAgo;
	}

	public Long getIdSet() {
		return idSet;
	}

	public void setIdSet(Long idSet) {
		this.idSet = idSet;
	}

	public Long getIdOut() {
		return idOut;
	}

	public void setIdOut(Long idOut) {
		this.idOut = idOut;
	}

	public Long getIdNov() {
		return idNov;
	}

	public void setIdNov(Long idNov) {
		this.idNov = idNov;
	}

	public Long getIdDez() {
		return idDez;
	}

	public void setIdDez(Long idDez) {
		this.idDez = idDez;
	}

	public GenericDTO getAprovador() {
		return aprovador;
	}

	public void setAprovador(GenericDTO aprovador) {
		this.aprovador = aprovador;
	}

	public GenericDTO getMeta() {
		return meta;
	}

	public void setMeta(GenericDTO meta) {
		this.meta = meta;
	}

	public GenericDTO getColaboradorMeta() {
		return colaboradorMeta;
	}

	public void setColaboradorMeta(GenericDTO colaboradorMeta) {
		this.colaboradorMeta = colaboradorMeta;
	}
}
