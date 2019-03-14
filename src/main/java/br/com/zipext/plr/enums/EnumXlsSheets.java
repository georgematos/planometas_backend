package br.com.zipext.plr.enums;

public enum EnumXlsSheets {
	
	METAS("FOLHA METAS");

	private String nome;
	
	private EnumXlsSheets(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
