package br.com.zipext.plr.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.FolhaMetaAnualModel;

public class FolhaMetaAnualDTO {
	
	private MetasDTO meta;
	
	private BigDecimal valorMeta;

	private BigDecimal valorRealizado;
	
	private BigDecimal valorPercentAtingido;
	
	private BigDecimal desempenho;

	public FolhaMetaAnualDTO() {}
	
	public FolhaMetaAnualDTO(FolhaMetaAnualModel model) {
		BeanUtils.copyProperties(model, this);
		this.meta = new MetasDTO(model.getPk().getMeta());
	}

	public BigDecimal getValorMeta() {
		return valorMeta;
	}

	public void setValorMeta(BigDecimal valorMeta) {
		this.valorMeta = valorMeta;
	}

	public BigDecimal getValorRealizado() {
		return valorRealizado;
	}

	public void setValorRealizado(BigDecimal valorRealizado) {
		this.valorRealizado = valorRealizado;
	}

	public BigDecimal getValorPercentAtingido() {
		return valorPercentAtingido;
	}

	public void setValorPercentAtingido(BigDecimal valorPercentAtingido) {
		this.valorPercentAtingido = valorPercentAtingido;
	}

	public BigDecimal getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(BigDecimal desempenho) {
		this.desempenho = desempenho;
	}

	public MetasDTO getMeta() {
		return meta;
	}

	public void setMeta(MetasDTO meta) {
		this.meta = meta;
	}
}
