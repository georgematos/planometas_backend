package br.com.zipext.plr.enums;

public enum EnumXlsIdCells {

	MATRICULA(2),
	NOME(4),
	CARGO(6),
	DIRETORIA(11);
	
	private int colIndex;
	
	private EnumXlsIdCells(int colIndex) {
		this.colIndex = colIndex;
	}
	
	public int getColIndex() {
		return this.colIndex;
	}
}
