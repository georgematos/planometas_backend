package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.enums.EnumSimNao;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.UsuarioModel;

public class UsuarioDTO {
	
	private String login;
	private String password;
	private String nome;
	private String matricula;
	private Character inPrimeiroAcesso;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(ColaboradorModel colaborador) {
		this.login = colaborador.getMatricula();
		this.password = colaborador.getMatricula();
		this.password = colaborador.getNome();
		this.inPrimeiroAcesso = EnumSimNao.SIM.getCodigo();
	}
	
	public UsuarioDTO(UsuarioModel usuario) {
		this.login = usuario.getLogin();
		this.inPrimeiroAcesso = usuario.getInPrimeiroAcesso();
		this.matricula = usuario.getColaborador().getMatricula();
	}
	
	public UsuarioModel getModel() {
		UsuarioModel model = new UsuarioModel();
		BeanUtils.copyProperties(this, model);
		
		return
				model;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Character getInPrimeiroAcesso() {
		return inPrimeiroAcesso;
	}

	public void setInPrimeiroAcesso(Character inPrimeiroAcesso) {
		this.inPrimeiroAcesso = inPrimeiroAcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDTO other = (UsuarioDTO) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
