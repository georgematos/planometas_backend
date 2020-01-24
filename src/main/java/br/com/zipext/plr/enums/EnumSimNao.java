package br.com.zipext.plr.enums;

public enum EnumSimNao {
	SIM('S', "Sim"),
	NAO('N', "Não");
	
	private Character codigo;
	private String descricao;
	
	private EnumSimNao(Character codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Character getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getValue() {
		return
				String.valueOf(codigo);
	}
	
	public static EnumSimNao forValue(String value) {
		for (EnumSimNao e : EnumSimNao.values()) {
			if (e.getCodigo().toString().equals(value) || e.getDescricao().equalsIgnoreCase(value)) {
				return
						e;
			}
		}
		
		return null;
	}
}
