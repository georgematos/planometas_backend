package br.com.zipext.plr.enums;

public enum EnumXlsSection {
	LOGO(0),
	ID(3),
	IBTIDA(6),
	INDIVIDUAL(8),
	PARTICIPACAO(9),
	PERFORMANCE(10),
	EXTRA(11),
	QUANTITATIVAS(13),
	PROJETOS(28),
	PONTUACAO(37);
	
	private int rowNum;
	
	private EnumXlsSection(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public int getRowNum() {
		return
				this.rowNum;
	}
}
