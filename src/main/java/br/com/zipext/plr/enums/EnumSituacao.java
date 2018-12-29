package br.com.zipext.plr.enums;

public enum EnumSituacao {
	ATIVO("A","Ativo"),
	INATIVO("I", "Inativo"),
	NA("N/A","Não aplicável");
	
	private String codigo;
	private String descricao;
	
	private EnumSituacao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EnumSituacao forValue(String value) {
		if (value == null) return NA;
		
		for (EnumSituacao situacao : EnumSituacao.values()) {
			if (situacao.getCodigo().equalsIgnoreCase(value) || situacao.getDescricao().equalsIgnoreCase(value)) {
				return situacao;
			}
		}
		
		return
				NA;
	}
}
