package br.com.zipext.plr.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.model.AfastamentoModel;
import br.com.zipext.plr.model.CargoModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.DiretoriaModel;
import br.com.zipext.plr.model.FilialModel;
import br.com.zipext.plr.model.TimeModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.utils.PLRUtils;

public class ColaboradorDTO {
	
	private boolean isNewColaborador;
	
	private String matricula;
	
	private String cpf;
	
	private String nome;
	
	private String situacao;
	
	private String elegivel;
	
	private String dataAdmissao;
	
	private String dataDemissao;
	
	private CargoDTO cargo;
	
	private ColaboradorResumoDTO superiorImediato;
	
	private DiretoriaDTO diretoria;
	
	private TimeDTO time;
	
	private GenericDTO filial;
	
	private UsuarioDTO usuario;
	
	private AfastamentoDTO afastamento;
	
	public ColaboradorDTO() {}
	
	public ColaboradorDTO(ColaboradorModel model) {
		BeanUtils.copyProperties(model, this);
		this.cargo = new CargoDTO(model.getCargo());
		this.diretoria = new DiretoriaDTO(model.getDiretoria());
		this.time = new TimeDTO(model.getTime());
		this.filial = new GenericDTO(model.getFilial());
		this.superiorImediato = new ColaboradorResumoDTO();
		
		if (model.getSuperiorImediato() != null) {
			this.superiorImediato.setMatricula(model.getSuperiorImediato());
		}
		
		this.dataAdmissao = model.getDataAdmissao() != null ? model.getDataAdmissao().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS)) : "";
		this.dataDemissao = model.getDataDemissao() != null ? model.getDataDemissao().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS)) : "";
		
		if (model.getUsuarios() != null) {
			//TODO: change relation between UsuarioModel and ColaboradorModel to OneToOne
			Optional<UsuarioModel> userOptional = model.getUsuarios().stream().findFirst();
			if (userOptional.isPresent()) {
				this.usuario = new UsuarioDTO(userOptional.get());				
			}
		}
		
		if (model.getAfastamentos() != null && !model.getAfastamentos().isEmpty()) {
			Optional<AfastamentoModel> afastamentoOptional = model.getAfastamentos()
																  .stream().filter(p -> p.getFimAfastamento() == null)
																  .findFirst();
			if (afastamentoOptional.isPresent()) {
				this.afastamento = new AfastamentoDTO(afastamentoOptional.get());
			}
		}
	}
	
	public ColaboradorModel obterModel() {
		ColaboradorModel model = new ColaboradorModel();
		BeanUtils.copyProperties(this, model);
		
		if (this.cargo != null) {
			model.setCargo(new CargoModel(this.cargo.getId()));
		}
		
		if (this.diretoria != null) {
			model.setDiretoria(new DiretoriaModel(this.diretoria.getId()));
		}
		
		if (this.filial != null) {
			model.setFilial(new FilialModel(this.filial.getId()));
		}
		
		if (this.time != null) {
			model.setTime(new TimeModel(this.time.getCodigo()));
		}
		
		if (StringUtils.isNotBlank(this.dataAdmissao)) {
			model.setDataAdmissao(LocalDate.parse(this.dataAdmissao.substring(0, 10), DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS)));
		}
		
		if (this.getSuperiorImediato() != null) {
			model.setSuperiorImediato(this.getSuperiorImediato().getMatricula());
		}
		
		if (StringUtils.isNotBlank(this.dataDemissao)) {
			model.setDataDemissao(LocalDate.parse(this.dataDemissao.substring(0, 10), DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS)));
		}
		
		model.setInclusao(LocalDateTime.now());
		model.setResponsavelInclusao(PLRUtils.SYS_USER);
		
		return model;
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
	
	public GenericDTO getFilial() {
		return filial;
	}

	public void setFilial(GenericDTO filial) {
		this.filial = filial;
	}
	
	public String getElegivel() {
		return elegivel;
	}

	public void setElegivel(String elegivel) {
		this.elegivel = elegivel;
	}
	
	public String getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(String dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(String dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	
	public boolean isNewColaborador() {
		return isNewColaborador;
	}

	public void setNewColaborador(boolean isNewColaborador) {
		this.isNewColaborador = isNewColaborador;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	public AfastamentoDTO getAfastamento() {
		return afastamento;
	}

	public void setAfastamento(AfastamentoDTO afastamento) {
		this.afastamento = afastamento;
	}
	
	public ColaboradorResumoDTO getSuperiorImediato() {
		return superiorImediato;
	}

	public void setSuperiorImediato(ColaboradorResumoDTO superiorImediato) {
		this.superiorImediato = superiorImediato;
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
