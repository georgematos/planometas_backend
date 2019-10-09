package br.com.zipext.plr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "AUTH", name = "ASS_PERFIL_LOGIN")
public class PerfilUsuarioModel {

	@EmbeddedId
	private PerfilUsuarioPK pk;
	
	@Column(name = "IN_SITUACAO")
	private Character situacao;
	
	public PerfilUsuarioModel() {}
	
	public PerfilUsuarioModel(PerfilModel perfil, UsuarioModel usuario) {
		this.pk = new PerfilUsuarioPK(perfil, usuario);
	}
	
	@Embeddable
	public static class PerfilUsuarioPK implements Serializable {
		
		private static final long serialVersionUID = 2072341220818378149L;

		@ManyToOne
		@JoinColumn(name = "CD_PERFIL")
		private PerfilModel perfil;
		
		@ManyToOne
		@JoinColumn(name = "CD_LOGIN")
		private UsuarioModel usuario;
		
		public PerfilUsuarioPK() {}
		
		public PerfilUsuarioPK(PerfilModel perfil, UsuarioModel usuario) {
			this.perfil = perfil;
			this.usuario = usuario;
		}

		public PerfilModel getPerfil() {
			return perfil;
		}

		public void setPerfil(PerfilModel perfil) {
			this.perfil = perfil;
		}

		public UsuarioModel getUsuario() {
			return usuario;
		}

		public void setUsuario(UsuarioModel usuario) {
			this.usuario = usuario;
		}
	}

	public PerfilUsuarioPK getPk() {
		return pk;
	}

	public void setPk(PerfilUsuarioPK pk) {
		this.pk = pk;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}
}
