package br.com.projetoIntegrador.projetoEcoIntegrador.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket docket(){
	return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis( RequestHandlerSelectors.basePackage
			("br.com.projetoIntegrador.projetoEcoIntegrador.controller") )
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());
			}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
		.title("Projeto Final")
		.description("Projeto Integrador")
		.version("1.0")
		.contact(contact())
		.build();
		}
		private Contact contact(){
		return new Contact("Gustavo Martins",
		"https://github.com/GustavoMarttiins",
		"Desenvolvedor Java jr. Generation Brasil");
		}

}

