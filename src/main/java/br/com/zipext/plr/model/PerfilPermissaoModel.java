package br.com.zipext.plr.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "AUTH", name = "ASS_PERFIL_PERMISSAO")
public class PerfilPermissaoModel {

	@EmbeddedId
	private PerfilPermissaoPK pk;
	
	public PerfilPermissaoModel() {}
	
	public PerfilPermissaoModel(PerfilModel perfil, PermissaoModel permissao) {
		this.pk = new PerfilPermissaoPK(perfil, permissao);
	}
	
	@Embeddable
	public static class PerfilPermissaoPK implements Serializable {
		
		private static final long serialVersionUID = 8834890749365974074L;

		@ManyToOne
		@JoinColumn(name = "CD_PERFIL")
		public PerfilModel perfil;
		
		@ManyToOne
		@JoinColumn(name = "CD_PERMISSAO")
		public PermissaoModel permissao;
		
		public PerfilPermissaoPK() {}
		
		public PerfilPermissaoPK(PerfilModel perfil, PermissaoModel permissao) {
			this.perfil = perfil;
			this.permissao = permissao;
		}

		public PerfilModel getPerfil() {
			return perfil;
		}

		public void setPerfil(PerfilModel perfil) {
			this.perfil = perfil;
		}

		public PermissaoModel getPermissao() {
			return permissao;
		}

		public void setPermissao(PermissaoModel permissao) {
			this.permissao = permissao;
		}
	}

	public PerfilPermissaoPK getPk() {
		return pk;
	}

	public void setPk(PerfilPermissaoPK pk) {
		this.pk = pk;
	}
}
