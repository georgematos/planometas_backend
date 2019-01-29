package br.com.zipext.plr.enums;

public enum EnumXlsEspecificasCells {
	
	SEQUENCIA(1),
	DESCRICAO(2),
	FREQUENCIA(5),
	PESOS(7),
	META(9),
	OBSERVACOES(10),
	PRAZOS(12),
	SUMPESOS(8),
	PONTUACAO(7);
	
	private int colIndex;
	
	private EnumXlsEspecificasCells(int colIndex) {
		this.colIndex = colIndex;
	}
	
	public int getColIndex() {
		return this.colIndex;
	}
}
