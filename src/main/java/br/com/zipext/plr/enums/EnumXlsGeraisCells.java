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
	
	public static EnumXlsGeraisCells getBonusIndexForMeta(int idMeta) {
		EnumXlsGeraisCells cell = null;
		switch (idMeta) {
		case 1:
			cell = BON_EBITDA;
			break;
		case 2:
			cell = BON_INDIV;
			break;
		case 3:
			cell = BON_PARTIC;
			break;
		case 4:
			cell = BON_PERFOR;
			break;
		default:
			break;
		}
		
		return cell;
	} 
}
