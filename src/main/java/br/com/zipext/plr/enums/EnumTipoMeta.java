package br.com.zipext.plr.enums;

public enum EnumTipoMeta {

	NOVALUE(-1, '-', "N/A"),
	GATILHO(1, 'G', "Gatilho"),
	CORPORATIVA(2, 'C', "Corporativas"),
	QUANTITATIVA(3, 'Q', "Metas Quantitativas"),
	ENTREGA(4, 'E', "Entrega"),
	TIME(5, 'T', "Time"),
	AVALIACAO(6, 'A', "Avaliação"),
	EXTRAS(7, 'X', "Extras"),
	PROJETO(8, 'P', "Metas de Projeto");

	private int id;
	private Character abv;
	private String nome;

	private EnumTipoMeta(int id, Character abv, String nome) {
		this.id = id;
		this.abv = abv;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public Character getAbv() {
		return abv;
	}

	public String getNome() {
		return nome;
	}

	public static EnumTipoMeta forId(int id) {
		for (EnumTipoMeta e : EnumTipoMeta.values()) {
			if (e.getId() == id) {
				return e;
			}
		}

		return NOVALUE;
	}
	
	public static EnumTipoMeta forAbv(Character abv) {
		for (EnumTipoMeta e : EnumTipoMeta.values()) {
			if (e.getAbv() == abv) {
				return e;
			}
		}

		return NOVALUE;
	}

}
