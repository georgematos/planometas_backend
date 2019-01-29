package br.com.zipext.plr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.zipext.plr.enums.EnumProperty;

@Service
public class PropertyService {

	@Value("${app.plr.export.template.path}")
	private String templatePath;

	@Value("${app.frontend.ext.ip}")
	private String appExternalHostIp;
	
	@Value("${app.frontend.int.ip}")
	private String appInternalHostIp;
	
	public String getProperty(EnumProperty property) {
		switch (property) {
		case XLS_TEMPLATE_PATH:
			return this.templatePath;
		case APP_EXTERNAL_HOST_IP:
			return this.appExternalHostIp;
		case APP_INTERNAL_HOST_IP:
			return this.appInternalHostIp;
		default:
			return null;
		}
	}
}
