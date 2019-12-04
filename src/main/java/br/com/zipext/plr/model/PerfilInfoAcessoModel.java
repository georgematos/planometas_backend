package br.com.zipext.plr.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "AUTH", name = "ASS_ACESSO_INFORMACAO_PERFIL")
public class PerfilInfoAcessoModel {

	@EmbeddedId
	private PerfilInfoAcessoPK pk;
	
	public PerfilInfoAcessoModel() {}
	
	public PerfilInfoAcessoModel(PerfilModel perfil, InformacaoAcessoModel informacaoAcesso) {
		this.pk = new PerfilInfoAcessoPK(perfil, informacaoAcesso);
	}
	
	public PerfilInfoAcessoPK getPk() {
		return pk;
	}

	public void setPk(PerfilInfoAcessoPK pk) {
		this.pk = pk;
	}

	@Embeddable
	public static class PerfilInfoAcessoPK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5147882269037467289L;

		@ManyToOne
		@JoinColumn(name = "CD_PERFIL")
		private PerfilModel perfil;
		
		@ManyToOne
		@JoinColumn(name = "CD_INFORMACAO")
		private InformacaoAcessoModel informacaoAcesso;
		
		public PerfilInfoAcessoPK() {}

		public PerfilInfoAcessoPK(PerfilModel perfil, InformacaoAcessoModel informacaoAcesso) {
			this.perfil = perfil;
			this.informacaoAcesso = informacaoAcesso;
		}

		public PerfilModel getPerfil() {
			return perfil;
		}

		public void setPerfil(PerfilModel perfil) {
			this.perfil = perfil;
		}

		public InformacaoAcessoModel getInformacaoAcesso() {
			return informacaoAcesso;
		}

		public void setInformacaoAcesso(InformacaoAcessoModel informacaoAcesso) {
			this.informacaoAcesso = informacaoAcesso;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((informacaoAcesso == null) ? 0 : informacaoAcesso.hashCode());
			result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
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
			PerfilInfoAcessoPK other = (PerfilInfoAcessoPK) obj;
			if (informacaoAcesso == null) {
				if (other.informacaoAcesso != null)
					return false;
			} else if (!informacaoAcesso.equals(other.informacaoAcesso))
				return false;
			if (perfil == null) {
				if (other.perfil != null)
					return false;
			} else if (!perfil.equals(other.perfil))
				return false;
			return true;
		}
	}
}
