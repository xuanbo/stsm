package com.whut.stsm.web.configuration.mvc;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by null on 2017/2/21.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    private static final String defaultEncoding = "UTF-8";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将web-ui模块打包后的ui路径下的静态资源映射到web模块的/ui/**路径下
        registry.addResourceHandler("/ui/**").addResourceLocations("classpath:/ui/");
        super.addResourceHandlers(registry);
    }

    /**
     * MessageSource
     * bean 的名字一定要是 messageSource ，因为源码是写死了。。
     *
     * @return ReloadableResourceBundleMessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(defaultEncoding);
        // ReloadableResourceBundleMessageSource要指定 classpath: 前缀
        messageSource.setBasename("classpath:message/valid");
        return messageSource;
    }

    /**
     * 数据校验
     *
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setProviderClass(HibernateValidator.class);
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }
}
