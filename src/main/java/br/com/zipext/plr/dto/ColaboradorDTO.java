package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.ColaboradorModel;

public class ColaboradorDTO {
	
	private String matricula;
	
	private String nome;
	
	private String situacao;
	
	private CargoDTO cargo;
	
	private DiretoriaDTO diretoria;
	
	private TimeDTO time;
	
	public ColaboradorDTO() {}
	
	public ColaboradorDTO(ColaboradorModel model) {
		BeanUtils.copyProperties(model, this);
		this.cargo = new CargoDTO(model.getCargo());
		this.diretoria = new DiretoriaDTO(model.getDiretoria());
		this.time = new TimeDTO(model.getTime());
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CargoDTO getCargo() {
		return cargo;
	}

	public void setCargo(CargoDTO cargo) {
		this.cargo = cargo;
	}

	public DiretoriaDTO getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(DiretoriaDTO diretoria) {
		this.diretoria = diretoria;
	}

	public TimeDTO getTime() {
		return time;
	}

	public void setTime(TimeDTO time) {
		this.time = time;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public DiretoriaDTO getDiretoriaOrElse() {
		if (this.diretoria != null) {
			return
					this.diretoria;
		}
		return new DiretoriaDTO();
	}
	
	public TimeDTO getTimeOrElse() {
		if (this.time != null) {
			return
					this.time;
		}
		
		return new TimeDTO();
	}
	
	public CargoDTO getCargoOrElse() {
		if (this.cargo != null) {
			return
					this.cargo;
		}
		
		return new CargoDTO();
	}
}
