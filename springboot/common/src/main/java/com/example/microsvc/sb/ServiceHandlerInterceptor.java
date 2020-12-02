package com.example.microsvc.sb;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ServiceHandlerInterceptor implements HandlerInterceptor {

    final static String HTTP_HEADER_CORRELATION_ID = "x-correlation-id";

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) {
        String corrId = request.getHeader(HTTP_HEADER_CORRELATION_ID);
        MDC.put(HTTP_HEADER_CORRELATION_ID, corrId != null ? corrId : "N/A");
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        MDC.clear();
    }
}
