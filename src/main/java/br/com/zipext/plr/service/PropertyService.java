package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.enums.EnumProperty;

@Service
public class PropertyService {

	@Value("${app.plr.export.template.folha.meta.path}")
	private String templateFolhaMetaPath;
	
	@Value("${app.plr.export.template.indicadores.path}")
	private String templateIndicadoresPath;

	@Value("${app.plr.export.template.colaboradores.path}")
	private String templateColaboradoresPath;

	@Value("${app.plr.export.template.cargos.path}")
	private String templateCargosPath;

	@Value("${app.plr.export.template.equivalencias.path}")
	private String templateEquivalenciasPath;

	@Value("${app.plr.export.template.diretorias.path}")
	private String templateDiretoriasPath;

	@Value("${app.plr.export.template.filiais.path}")
	private String templateFiliaisPath;

	@Value("${app.plr.export.template.times.path}")
	private String templateTimesPath;

	@Value("${app.plr.export.template.tipos.meta.path}")
	private String templateTiposMetaPath;

	@Value("${app.plr.export.template.tipos.medicao.path}")
	private String templateTiposMedicaoPath;

	@Value("${app.plr.export.template.formula.path}")
	private String templateFormulaPath;

	@Value("${app.plr.export.template.frequencia.medicao.path}")
	private String templateFrequenciaMedicaoPath;

	@Value("${app.plr.export.template.escalonamento.path}")
	private String templateEscalonamentoPath;

	@Value("${app.plr.export.template.folhas.metas.mensais.path}")
	private String templateFolhasMetasMensaisPath;
	
	@Value("${app.plr.export.template.consulta.folhas.metas.path}")
	private String templateConsultaFolhasMetasPath;
	
	@Value("${app.plr.export.template.escalonamento.qualitativo.path}")
	private String templateEscalonamentoQualitativoPath;
	
	@Value("${app.plr.export.template.avaliacao.qualitativa.path}")
	private String templateAvaliacaoQualitativaPath;
	
	@Value("${app.frontend.ext.ip}")
	private String appExternalHostIp;
	
	@Value("${app.frontend.int.ip}")
	private String appInternalHostIp;
	
	public String getProperty(EnumProperty property) {
		switch (property) {
		case XLS_TEMPLATE_FOLHA_META_PATH:
			return this.templateFolhaMetaPath;
		case XLS_TEMPLATE_INDICADORES_PATH:
			return this.templateIndicadoresPath;
		case XLS_TEMPLATE_COLABORADORES_PATH:
			return this.templateColaboradoresPath;
		case XLS_TEMPLATE_CARGOS_PATH:
			return this.templateCargosPath;
		case XLS_TEMPLATE_EQUIVALENCIAS_PATH:
			return this.templateEquivalenciasPath;
		case XLS_TEMPLATE_DIRETORIAS_PATH:
			return this.templateDiretoriasPath;
		case XLS_TEMPLATE_FILIAIS_PATH:
			return this.templateFiliaisPath;
		case XLS_TEMPLATE_TIMES_PATH:
			return this.templateTimesPath;
		case XLS_TEMPLATE_TIPOS_META_PATH:
			return this.templateTiposMetaPath;
		case XLS_TEMPLATE_TIPOS_MEDICAO_PATH:
			return this.templateTiposMedicaoPath;
		case XLS_TEMPLATE_FORMULA_PATH:
			return this.templateFormulaPath;
		case XLS_TEMPLATE_FREQUENCIA_MEDICAO_PATH:
			return this.templateFrequenciaMedicaoPath;
		case XLS_TEMPLATE_ESCALONAMENTO_PATH:
			return this.templateEscalonamentoPath;
		case XLS_TEMPLATE_FOLHAS_METAS_MENSAIS_PATH:
			return this.templateFolhasMetasMensaisPath;
		case XLS_TEMPLATE_CONSULTA_FOLHAS_METAS_PATH:
			return this.templateConsultaFolhasMetasPath;
		case XLS_TEMPLATE_ESCALONAMENTO_QUALI_PATH:
			return this.templateEscalonamentoQualitativoPath;
		case XLS_TEMPLATE_AVALIACAO_QUALI_PATH:
			return this.templateAvaliacaoQualitativaPath;
		case APP_EXTERNAL_HOST_IP:
			return this.appExternalHostIp;
		case APP_INTERNAL_HOST_IP:
			return this.appInternalHostIp;
		default:
			return null;
		}
	}
}
