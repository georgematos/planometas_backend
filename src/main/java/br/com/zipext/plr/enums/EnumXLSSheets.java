package br.com.zipext.plr.enums;

public enum EnumXLSSheets {
	
	FOLHA_METAS("Folha de Metas"),
	INDICADORES("Indicadores"),
	COLABORADORES("Colaboradores");

	private String nome;
	
	private EnumXLSSheets(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
