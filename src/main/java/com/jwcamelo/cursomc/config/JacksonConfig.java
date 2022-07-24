package com.jwcamelo.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwcamelo.cursomc.domain.PagamentoComBoleto;
import com.jwcamelo.cursomc.domain.PagamentoComCartao;

@Configuration
public class JacksonConfig {
	@Bean
	public Jackson2ObjectMapperBuilder objMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
		public void configure(ObjectMapper objMapper) {
			objMapper.registerSubtypes(PagamentoComCartao.class);
			objMapper.registerSubtypes(PagamentoComBoleto.class);
			super.configure(objMapper);
			}
		};
		return builder;
	}
}
