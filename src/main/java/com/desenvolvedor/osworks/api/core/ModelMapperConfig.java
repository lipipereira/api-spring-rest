package com.desenvolvedor.osworks.api.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Componente do Spring com o objeto de definição de configuração  	
@Configuration
public class ModelMapperConfig {
	// metodo para retorna uma Instacia da Classe ModelMapper 
	// Anotação Bean indica que esse metod inicializa configura  pelo Spring
	@Bean
	public ModelMapper modelMapper() {
	      return new ModelMapper();
	}
}
