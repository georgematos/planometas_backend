package br.com.zipext.plr.enums;

public enum EnumXlsGeraisCells {

	DESCRICAO(1),
	VALOR(5),
	BONUS(7),
	OBS(9);
	
	private int colIndex;
	
	private EnumXlsGeraisCells(int colIndex) {
		this.colIndex = colIndex;
	}
	
	public int getColIndex() {
		return this.colIndex;
	}
}
