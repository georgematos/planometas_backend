package br.com.zipext.plr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.zipext.plr.enums.EnumProperty;
import br.com.zipext.plr.service.PropertyService;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private PropertyService propertyService;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/colaboradores/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/metasitem/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/usuarios/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/metas/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/folhametas/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/perfis/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/filiais/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/diretorias/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/cargos/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/equivalencias/**")
		.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
		.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/times/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET");
		registry.addMapping("/formulas/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET");
		registry.addMapping("/tiposmeta/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET");
		registry.addMapping("/tiposmedicao/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET");
		registry.addMapping("/frequenciasmedicao/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET");
		registry.addMapping("/tempo/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET");
		registry.addMapping("/folhasmensais/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET", "POST", "DELETE", "PUT");
		registry.addMapping("/metasperiodo/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET", "POST", "DELETE", "PUT");
		registry.addMapping("/avaliacao/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/avalprazo/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/avalquali/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/metaaval/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/afastamento/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
	
	}
}
