package br.com.zipext.plr.enums;

public enum EnumXlsGeraisCells {

	VAL_EBITDA(5),
	BON_EBITDA(7),
	OBS_EBITDA(8),
	BON_INDIV(7),
	OBS_INDIV(8),
	BON_PARTIC(7),
	OBS_PARTIC(8),
	BON_PERFOR(7),
	OBS_PERFOR(8);
	
	private int colIndex;
	
	private EnumXlsGeraisCells(int colIndex) {
		this.colIndex = colIndex;
	}
	
	public int getColIndex() {
		return this.colIndex;
	}
}
