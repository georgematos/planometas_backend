package br.com.zipext.plr.enums;

public enum EnumXlsLogoCells {

	EXPORT_DATE(2);
	
	private int index;
	
	private EnumXlsLogoCells(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return this.index;
	}
}
