package br.com.zipext.plr.enums;

public enum EnumXLSTemplates {
	FOLHA_METAS(1L),
	INDICADORES(2L),
	COLABORADORES(3L);
	
	private Long codigo;
	
	private EnumXLSTemplates(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}
}
