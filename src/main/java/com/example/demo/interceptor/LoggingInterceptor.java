package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override // 컨트롤러에 전달되기 전에 실행됩니다.
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Before handling the request - URL: {}", request.getRequestURL());

        request.setAttribute("startTime", System.currentTimeMillis());
        return true; // true를 반환하여 요청을 계속 진행
    }

    @Override // 컨트롤러에서 요청을 처리한 후 뷰가 렌더링 되기 전에 실행됩니다.
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("After handling the request - URL: {}", request.getRequestURL());

        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");

        long endTime = System.currentTimeMillis();
        modelAndView.addObject("handlingTime", endTime - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Request completed - URL: {}", request.getRequestURL());

        if (ex != null) {
            log.error("Exception occurred: {}", ex.getMessage());
        }
    }
}
