package br.com.zipext.plr.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.zipext.plr.model.ColaboradorModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColaboradorDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private String matricula;
	private Character situacao;
	
	private List<CargoDTO> cargos;
	
	public ColaboradorDTO() {}
	
	public ColaboradorDTO(ColaboradorModel model) {
		this.cargos = new ArrayList<>();
		if (model != null) {
			BeanUtils.copyProperties(model, this);
			model.getColaboradoresCargos().forEach(cc -> cargos.add(new CargoDTO(cc.getPk().getCargo())));
		}
	}
	
	public ColaboradorModel getModel() {
		ColaboradorModel model = new ColaboradorModel();
		BeanUtils.copyProperties(this, model);
		
		return model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public List<CargoDTO> getCargos() {
		return cargos;
	}

	public void setCargos(List<CargoDTO> cargos) {
		this.cargos = cargos;
	}
}
