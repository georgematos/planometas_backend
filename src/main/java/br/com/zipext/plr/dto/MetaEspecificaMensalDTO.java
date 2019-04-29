package br.com.zipext.plr.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.HistoricoMetaEspecificaMensalModel;
import br.com.zipext.plr.model.MesModel;
import br.com.zipext.plr.model.MetaEspecificaMensalModel;
import br.com.zipext.plr.model.MetaEspecificaMensalModel.MetaEspecificaMensalPK;
import br.com.zipext.plr.model.MetaEspecificaModel;

public class MetaEspecificaMensalDTO {
	
	private Long idMeta;
	
	private String matricula;
	
	private Integer sequencia;
	
	private Integer numMes;
	
	private String mes;
	
	private BigDecimal valorMeta;
	
	private BigDecimal valorRealizado;

	public MetaEspecificaMensalDTO() {}
	
	public MetaEspecificaMensalDTO(MetaEspecificaMensalModel model) {
		BeanUtils.copyProperties(model, this);
		this.mes = model.getPk().getMes().getMes();
		this.idMeta = model.getPk().getColaboradorMetaEspecifica().getIdMeta();
		this.numMes = model.getPk().getMes().getNumMes();
		this.matricula = model.getPk().getColaboradorMetaEspecifica().getPk().getColaborador().getMatricula();
		this.sequencia = model.getPk().getColaboradorMetaEspecifica().getPk().getSequencia();
	}
	
	public MetaEspecificaMensalDTO(HistoricoMetaEspecificaMensalModel historico) {
		BeanUtils.copyProperties(historico, this);
		MesModel mes = historico.getPk().getMetaEspecificaMensal().getPk().getMes();
		this.idMeta = historico.getPk().getMetaEspecificaMensal().getPk().getColaboradorMetaEspecifica().getIdMeta();
		this.numMes = mes.getNumMes();
		this.mes = mes.getMes();
		this.matricula = historico.getPk().getMetaEspecificaMensal().getPk().getColaboradorMetaEspecifica().getPk().getColaborador().getMatricula();
		this.sequencia = historico.getPk().getMetaEspecificaMensal().getPk().getColaboradorMetaEspecifica().getSequencia();
	}
	
	public MetaEspecificaMensalModel obterModelFromDTO() {
		MetaEspecificaMensalModel model = new MetaEspecificaMensalModel();
		
		BeanUtils.copyProperties(this, model);
		
		MetaEspecificaMensalPK pk = new MetaEspecificaMensalPK();
		pk.setColaboradorMetaEspecifica(new ColaboradorMetaEspecificaModel(new ColaboradorModel(this.matricula), new MetaEspecificaModel(this.idMeta), this.sequencia));
		pk.setMes(new MesModel(this.numMes.longValue()));
		model.setPk(pk);
		
		return
				model;
	}
	
	public Long getIdMeta() {
		return idMeta;
	}

	public void setIdMeta(Long idMeta) {
		this.idMeta = idMeta;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
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

	public Integer getNumMes() {
		return numMes;
	}

	public void setNumMes(Integer numMes) {
		this.numMes = numMes;
	}
}
