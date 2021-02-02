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
		case APP_EXTERNAL_HOST_IP:
			return this.appExternalHostIp;
		case APP_INTERNAL_HOST_IP:
			return this.appInternalHostIp;
		default:
			return null;
		}
	}
}
