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
	
	public String getCodigoToString() {
		return
				String.valueOf(codigo);
	}
}
