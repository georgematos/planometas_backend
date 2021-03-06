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
	TIPOS_META(9L),
	TIPOS_MEDICAO(10L),
	FORMULA(11L),
	FREQUENCIA_MEDICAO(12L),
	ESCALONAMENTO(13L),
	FOLHAS_METAS_MENSAIS(14L),
	CONSULTA_FOLHAS_METAS(15L),
	ESCALONAMENTO_QUALI(16L),
	AVALIACAO_QUALI(17L);

	private Long codigo;
	
	private EnumXLSTemplates(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}
}
