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
		registry.addMapping("/metaEspecifica/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/usuarios/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
		registry.addMapping("/historico/**")
			.allowedOrigins(this.propertyService.getProperty(EnumProperty.APP_INTERNAL_HOST_IP), this.propertyService.getProperty(EnumProperty.APP_EXTERNAL_HOST_IP))
			.allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS");
	}
}
