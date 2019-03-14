package br.com.zipext.plr.enums;

public enum EnumXlsMensaisSection {
	
	RESUMO_META(0),
	METAS_PLANEJADAS(2),
	METAS_REALIZADAS(3),
	AGG_PLANEJADAS(6),
	AGG_REALIZADAS(7);
	
	private int rowNum;
	
	private EnumXlsMensaisSection(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public int getRowNum() {
		return
				this.rowNum;
	}

}
