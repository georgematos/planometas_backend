package br.com.zipext.plr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CAD_LOGIN", schema = "BET_PLR")
public class UsuarioModel {

	@Id
	@Column(name = "CD_LOGIN")
	private String login;
	
	@Column(name = "DS_PASSWORD")
	private String password;
	
	@Column(name = "DS_LOGIN")
	private String nome;
	
	@Column(name = "IN_PRIMEIRO_ACESSO")
	private Character inPrimeiroAcesso;
	
	@ManyToOne
	@JoinColumn(name = "CD_MATRICULA")
	private ColaboradorModel colaborador;
	
	@Transient
	private Character resetPassword;
	
	public UsuarioModel() {}
	
	public UsuarioModel(String login) {
		this.login = login;
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

	public ColaboradorModel getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorModel colaborador) {
		this.colaborador = colaborador;
	}
	
	public Character getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(Character resetPassword) {
		this.resetPassword = resetPassword;
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
		UsuarioModel other = (UsuarioModel) obj;
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
