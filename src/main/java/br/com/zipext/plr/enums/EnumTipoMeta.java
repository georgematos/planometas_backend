package br.com.zipext.plr.enums;

public enum EnumTipoMeta {
	
	QUANTITATIVA(1, 'Q', "Metas Quantitativas"),
	PROJETOS(2, 'P', "Metas de Projeto"),
	GATILHO(-1, 'G', "Gatilho"),
	CORPORATIVAS(-1, 'C', "Corporativas"),
	TIME(-1, 'T', "Time"),
	AVALIACAO(-1, 'A', "Avaliação"),
	EXTRAS(-1, 'E', "Extras"),
	NOVALUE(-1, '-', "N/A");
	
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
				return
						e;
			}
		}
		
		return NOVALUE;
	}
}
