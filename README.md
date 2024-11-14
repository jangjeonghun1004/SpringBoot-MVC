# HandlerInterceptor

    1. 인터셉터 구현
       LoggingInterceptor.java
    
    2. 인터셉터 등록
       InterceptorConfig.java

    preHandle: 요청이 컨트롤러에 전달되기 전에 실행됩니다.
    postHandle: 컨트롤러에서 요청을 처리한 후, 뷰가 렌더링되기 전에 실행됩니다.
    afterCompletion: 뷰가 렌더링된 후 실행됩니다.

# JSTL VS 스프링 태그 라이브러리

    <dependency>
        <groupId>jakarta.servlet.jsp.jstl</groupId>
        <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    </dependency>
    <dependency>
        <groupId>org.glassfish.web</groupId>
        <artifactId>jakarta.servlet.jsp.jstl</artifactId>
    </dependency>

    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <!DOCTYPE html>
    <html>
    <head>
        <title>JSTL Example</title>
    </head>
    <body>
        <h1>Handling Time: ${handlingTime} ms</h1>
        <p>Handling Time: <c:out value="${handlingTime}"/></p>
    </body>
    </html>

# locale(다국어)

    1. localeResolver 빈 생성
       "LocaleConfig" 파일을 참고하세요.

       LocaleResolver
       "localeResolver" 빈(bean)을 생성하면 DispatcherServlet이 자동으로 감지합니다.

    2. LocaleChangeInterceptor
       "InterceptorConfig" 파일을 참고하세요.

       Http 요청에 특정한 매개변수가 존재하는지 자동으로 감지한다.
       "# 헤더를 이용한 locale 설정"에는 LocaleChangeInterceptor 가 동작하지 않습니다.
       Cannot change HTTP Accept-Language header - use a different locale resolution strategy

    3. ResourceBundleMessageSource 빈 생성
       "LocaleConfig" 파일을 참고하세요.

    4. MessageSource
       "<spring:message>" 스프링 태그 라이브러리를 사용하면 
       DispatcherServlet 이 자동으로 "messages" 리소스 번들 적용한다.

    # 헤더를 이용한 locale 설정
        기본적으로 사용하는 브라우저에서 "Accept-Lanaguage" 를 헤더에 설정해서 전송한다.
        다시 말해 내가 setDefaultLocale()로 기본 언어를 설정해도 최종적으로는 브라우저에서 요청을 하기때문에
        매 요청시마다 헤더의 "Accept-Language" 를 체크해야 한다는 의미이다.

    # 세션을 이용한 localse 설정
    # 쿠키를 이용한 localse 설정

    # 한글 깨짐 현상 체크 사항
    1. resourceBundleMessageSource.setDefaultEncoding("UTF-8"); 체크
    2. file encoding 체(인텔리제이 -> settings -> file encoding)

    # <spring:~> 스프링 태그를 사용하기 위한 설정
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>

# @ExceptionHandler, @ControllerAdvice

    Spring Framework 는 기본적으로 "error.jsp" 를 루트 경로에 추가하면 자동으로 감지합니다.
    # 사용자 정의 예외 페이지 추가
        1. 새로운 클래스 파일 생성 후 "extends RuntimeException" 합니다.
        2. @ExceptionHandler("클래스명.class")로 설정합니다.
        3. 사용자 예외 페이지를 생성합니다.

    "ControllerAdviceConfig.java, handlerExceptionCustom.jsp, HandlerExceptionCustom.java"
    위 파일들을 참고하세요.

# Spring Framework Validator

    1. <form:form> 구성
        Spring Framework 의 <form:form> 태그를 사용해서 form UI를 구성합니다.
        "validatorView.jsp" 파일을 참고하세요.

    2. Model 객체 생성
        modelAttribute 를 위한 객체를 생성합니다.
        "ValidatorModel.java" 파일을 참고하세요.

    3. Validator 객체 생성
        Spring Framework 의 <form:form> 태그를 사용해서 구성한 항목을
        어떤 방식으로 유효성을 검증할 것인지에 대한 "Validator" 객체를 생성합니다.
        "CheckValidator.java" 파일을 참고하세요.

    4. Controller 와 Validator 연결
        유효성 검증을 위해 생성한 "Validator 객체" 와 "Controller 객체"를 서로 연결 합니다.
        "ValidatorController.java" 파일을 참고하세요.