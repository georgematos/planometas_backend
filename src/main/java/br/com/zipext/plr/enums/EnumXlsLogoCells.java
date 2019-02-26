package br.com.zipext.plr.enums;

public enum EnumXlsLogoCells {

	NUM_DOC(11);
	
	private int index;
	
	private EnumXlsLogoCells(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return this.index;
	}
}
