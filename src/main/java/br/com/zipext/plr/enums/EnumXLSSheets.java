package br.com.zipext.plr.enums;

public enum EnumXLSSheets {
	
	FOLHA_METAS("Folha de Metas"),
	INDICADORES("Indicadores"),
	COLABORADORES("Colaboradores"),
	CARGOS("Cargos"),
	EQUIVALENCIAS("Equivalencias"),
	DIRETORIAS("Diretorias"),
	FILIAIS("Filiais"),
	TIMES("Times"),
	TIPOS_METAS("Tipos de Metas"),
	TIPOS_MEDICAO("Tipos de Medicao"),
	FORMULA("Formula"),
	FREQUENCIA_MEDICAO("Frequencia de Medicao");

	private String nome;
	
	private EnumXLSSheets(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
