package br.com.zipext.plr.enums;

public enum EnumXLSArea {

	FOLHA_META("FOLHA META"),
	ITEM_FOLHA("ITEM FOLHA META"),
	INDICADORES("INDICADORES"),
	COLABORADORES("COLABORADORES"),
	CARGOS("CARGOS"),
	EQUIVALENCIAS("EQUIVALENCIAS"),
	DIRETORIAS("DIRETORIAS"),
	FILIAIS("FILIAIS"),
	TIMES("TIMES"),
	TIPOS_META("TIPOS META"),
	TIPOS_MEDICAO("TIPOS MEDICAO"),
	FORMULA("FORMULA"),
	FREQUENCIA_MEDICAO("FREQUENCIA MEDICAO"),
	ESCALONAMENTO("ESCALONAMENTO"),
	FOLHAS_METAS_MENSAIS("FOLHAS METAS MENSAIS");
	
	private String area;
	
	private EnumXLSArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return area;
	}
}
