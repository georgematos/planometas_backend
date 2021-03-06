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
	FREQUENCIA_MEDICAO("Frequencia de Medicao"),
	ESCALONAMENTO("Escalonamentos"),
	FOLHAS_METAS_MENSAIS("Folhas Metas Mensais"),
	CONSULTA_FOLHAS_METAS("Consulta Folhas Metas"),
	ESCALONAMENTO_QUALI("Escalonamento Qualitativo"),
	AVALIACAO_QUALI("Avaliacao Qualitativa");

	private String nome;
	
	private EnumXLSSheets(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
