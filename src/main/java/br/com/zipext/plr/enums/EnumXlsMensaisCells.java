package br.com.zipext.plr.enums;

public enum EnumXlsMensaisCells {
	
	SEQUENCIA_META(1),
	DESCRICAO_META(2),
	AGG_SUM(1),
	AGG_AVG(2),
	JAN(2, 1),
	FEV(3, 2),
	MAR(4, 3),
	ABR(5, 4),
	MAI(6, 5),
	JUN(7, 6),
	JUL(8, 7),
	AGO(9, 8),
	SET(10, 9),
	OUT(11, 10),
	NOV(12, 11),
	DEZ(13, 12),
	NOVALUE(-1, -1);
	
	private int colIndex;
	private int nuMes;
	
	private EnumXlsMensaisCells(int colIndex) {
		this.colIndex = colIndex;
	}
	
	private EnumXlsMensaisCells(int colIndex, int nuMes) {
		this.colIndex = colIndex;
		this.nuMes = nuMes;
	}

	public int getColIndex() {
		return colIndex;
	}

	public int getNuMes() {
		return nuMes;
	}
	
	public static EnumXlsMensaisCells forMes(int nuMes) {
		for (EnumXlsMensaisCells e : EnumXlsMensaisCells.values()) {
			if (e.getNuMes() == nuMes) {
				return
						e;
			}
		}
		
		return NOVALUE;
	}
}
