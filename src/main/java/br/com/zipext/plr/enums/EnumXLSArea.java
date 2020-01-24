package br.com.zipext.plr.enums;

public enum EnumXLSArea {

	FOLHA_META("FOLHA META"),
	ITEM_FOLHA("ITEM FOLHA META"),
	INDICADORES("INDICADORES"),
	COLABORADORES("COLABORADORES");
	
	private String area;
	
	private EnumXLSArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return area;
	}
}
