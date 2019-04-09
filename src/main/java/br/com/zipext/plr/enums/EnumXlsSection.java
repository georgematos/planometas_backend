package br.com.zipext.plr.enums;

public enum EnumXlsSection {
	LOGO(0),
	ID(3),
	GERAIS(6),
	QUANTITATIVAS(17),
	PROJETOS(39),
	PONTUACAO(55);
	
	private int rowNum;
	
	private EnumXlsSection(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public int getRowNum() {
		return
				this.rowNum;
	}
}
