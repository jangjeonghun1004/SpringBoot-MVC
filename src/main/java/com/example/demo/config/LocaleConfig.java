package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.Duration;
import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("locale");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");

        return resourceBundleMessageSource;
    }

    @Bean // 세션을 이용한 locale 설정
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);

        return sessionLocaleResolver;
    }

//    @Bean // 쿠키를 이용한 locale 설정
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver("language");
//        // Duration.ZERO 를 즉시 삭제, setCookieMaxAge 미설정시 브라우저 종료시 삭제
//        //cookieLocaleResolver.setCookieMaxAge(Duration.ZERO);
//        cookieLocaleResolver.setCookieMaxAge(Duration.ofMinutes(5));
//        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//
//        return cookieLocaleResolver;
//    }

//    @Bean // 헤더를 이용한 locale 설정(고정 헤더 키 = Accept-Language)
//    public LocaleResolver localeResolver() {
//        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
//        acceptHeaderLocaleResolver.setDefaultLocale(Locale.KOREA);
//
//        return acceptHeaderLocaleResolver;
//    }

}
