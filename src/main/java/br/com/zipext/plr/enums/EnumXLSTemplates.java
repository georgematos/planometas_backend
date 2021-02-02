package br.com.zipext.plr.enums;

public enum EnumXLSTemplates {
	FOLHA_METAS(1L),
	INDICADORES(2L),
	COLABORADORES(3L),
	CARGOS(4L),
	EQUIVALENCIAS(5L),
	DIRETORIAS(6L),
	FILIAIS(7L),
	TIMES(8L),
	TIPOS_META(9L);

	private Long codigo;
	
	private EnumXLSTemplates(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}
}
