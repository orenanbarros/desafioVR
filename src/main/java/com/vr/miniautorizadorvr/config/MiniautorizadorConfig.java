package com.vr.miniautorizadorvr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MiniautorizadorConfig implements ApplicationListener<ApplicationEvent> {
	
	@Autowired
	Environment env;
	
	public static long SALDO_INICIAL_CARTAO;
	
	
	@Bean
	   public MessageSource messageSource() {
	      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	      messageSource.setBasename("classpath:messages");
	      messageSource.setDefaultEncoding("ISO-8859-1");
	      return messageSource;
	   }
	
	@Bean
	 public LocalValidatorFactoryBean validator(MessageSource messageSource) {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource);
	    return bean;
	 }

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		this.SALDO_INICIAL_CARTAO = Long.parseLong(env.getProperty("saldoInicialCartao"));	
	}
	
	

}
