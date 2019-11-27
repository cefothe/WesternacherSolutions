package com.westernacher.solutions.usermanagement.configuration;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration is responsible to create and configure {@link ModelMapper} and register it
 * spring context with scope singelton
 */
@Configuration
public class ModelMapperConfiguration {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
