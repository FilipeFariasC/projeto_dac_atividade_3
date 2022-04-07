package br.edu.ifpb.dac.atividade3.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class Setup {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	//*
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    
	    messageSource.setBasename("classpath:src/main/resources/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    
	    messageSource.setCacheSeconds(10); //reload messages every 10 seconds
	    
	    
	    return messageSource;
	}
	/*
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	*/
}
