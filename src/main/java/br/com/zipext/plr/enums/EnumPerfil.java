package br.com.zipext.plr.enums;

import br.com.zipext.plr.model.PerfilModel;

public enum EnumPerfil {
	
	ADMIN(1L, "Administrador"),
	GENERICO(2L, "Genérico"),
	CADASTRADOR(3L, "Cadastrador");
	
	private Long id;
	private String nome;
	
	private EnumPerfil(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public static PerfilModel buildPerfilAdmin() {
		return new PerfilModel(ADMIN.getId());
	}
	
	public static PerfilModel buildPerfilGenerico() {
		return new PerfilModel(GENERICO.getId());
	}
}
