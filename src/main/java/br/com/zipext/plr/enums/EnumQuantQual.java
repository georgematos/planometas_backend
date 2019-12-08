package br.com.zipext.plr.enums;

public enum EnumQuantQual {

	QUANTITATIVA("1"),
	QUALITATIVA("2");
	
	private String codigo;
	
	private EnumQuantQual(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
}
