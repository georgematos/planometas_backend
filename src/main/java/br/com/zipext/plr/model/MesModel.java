package br.com.zipext.plr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "BET_PLR", name = "CAD_MES")
public class MesModel {

	@Id
	@Column(name = "CD_MES")
	private Long id;
	
	@Column(name = "DS_MES")
	private String mes;
	
	@Column(name = "DS_MES_ABV")
	private String mesAbreviado;
	
	@Column(name = "NU_MES")
	private Integer numMes;

	public MesModel() {}
	
	public MesModel(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getMesAbreviado() {
		return mesAbreviado;
	}

	public void setMesAbreviado(String mesAbreviado) {
		this.mesAbreviado = mesAbreviado;
	}

	public Integer getNumMes() {
		return numMes;
	}

	public void setNumMes(Integer numMes) {
		this.numMes = numMes;
	}
}
