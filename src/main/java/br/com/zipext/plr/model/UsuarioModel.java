package br.com.zipext.plr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "AUTH", name = "CAD_LOGIN")
public class UsuarioModel {

	@Id
	@Column(name = "CD_LOGIN")
	private String login;
	
	@Column(name = "DS_HASH")
	private String hash;
	
	@Column(name = "DS_NOME")
	private String nome;
	
	@Column(name = "IN_PRIMEIRO_ACESSO")
	private String inPrimeiroAcesso;
	
	@ManyToOne
	@JoinColumn(name = "CD_MATRICULA")
	private ColaboradorModel colaborador;
	
	@OneToMany(mappedBy = "pk.usuario")
	private List<PerfilUsuarioModel> perfisUsuario;
	
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

	public String getInPrimeiroAcesso() {
		return inPrimeiroAcesso;
	}

	public void setInPrimeiroAcesso(String inPrimeiroAcesso) {
		this.inPrimeiroAcesso = inPrimeiroAcesso;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public List<PerfilUsuarioModel> getPerfisUsuario() {
		return perfisUsuario;
	}

	public void setPerfisUsuario(List<PerfilUsuarioModel> perfisUsuario) {
		this.perfisUsuario = perfisUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
}
