package br.com.zipext.plr.dto;

import br.com.zipext.plr.model.PerfilModel;
import br.com.zipext.plr.model.PerfilUsuarioModel;
import br.com.zipext.plr.model.UsuarioModel;
import br.com.zipext.plr.model.PerfilUsuarioModel.PerfilUsuarioPK;

public class PerfilUsuarioDTO {
	
	private PerfilDTO perfil;
	
	private GenericDTO usuario;
	
	private String situacao;
	
	public PerfilUsuarioDTO() {}
	
	public PerfilUsuarioDTO(PerfilUsuarioModel model) {
		if (model != null) {
			this.perfil = new PerfilDTO(model.getPk().getPerfil());
			this.usuario = new GenericDTO(model.getPk().getUsuario());
			this.situacao = model.getSituacao();
		}
	}
	
	public PerfilUsuarioModel obterModel() {
		PerfilUsuarioModel model = new PerfilUsuarioModel();
		
		PerfilUsuarioPK pk = new PerfilUsuarioPK
				(new PerfilModel(this.perfil.getId()), new UsuarioModel(this.usuario.getLogin()));
		model.setPk(pk);
		model.setSituacao(this.situacao);
		
		return model;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}

	public GenericDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(GenericDTO usuario) {
		this.usuario = usuario;
	}
}
