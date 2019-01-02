package br.com.zipext.plr.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.ColaboradorMetaGeralModel;
import br.com.zipext.plr.utils.PLRUtils;

public class MetasDTO {

	private BigDecimal bonus;
	private BigDecimal valor;
	private String prazo;
	private ColaboradorDTO colaborador;
	
	public MetasDTO() {}
	
	public MetasDTO(ColaboradorMetaGeralModel model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			this.prazo = model.getPrazo() != null ? 
					model.getPrazo().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN)) : "";
			this.colaborador = new ColaboradorDTO(model.getPk().getColaborador());
		}
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public ColaboradorDTO getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorDTO colaborador) {
		this.colaborador = colaborador;
	}
}
