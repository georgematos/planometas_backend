package br.com.zipext.plr.dto;

import org.springframework.beans.BeanUtils;

import br.com.zipext.plr.enums.EnumSimNao;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.utils.PLRUtils;

public class UsuarioDTO {
	
	private String login;
	private String hash;
	private String phrase;
	private String nome;
	private String matricula;
	private Character inPrimeiroAcesso;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(ColaboradorModel colaborador) {
		this.login = colaborador.getMatricula();
		this.hash = colaborador.getMatricula();
		this.nome = colaborador.getNome();
		this.inPrimeiroAcesso = EnumSimNao.SIM.getCodigo();
	}
	
	public UsuarioDTO(UsuarioModel usuario) {
		this.login = usuario.getLogin();
		this.nome = usuario.getNome();
		this.inPrimeiroAcesso = usuario.getInPrimeiroAcesso();
		this.matricula = usuario.getColaborador().getMatricula();
		this.hash = usuario.getHash();
		this.phrase = PLRUtils.genPhrase(usuario.getColaborador().getNome(), Long.valueOf(usuario.getColaborador().getMatricula()));			
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
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
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
	
	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
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
		UsuarioDTO other = (UsuarioDTO) obj;
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
