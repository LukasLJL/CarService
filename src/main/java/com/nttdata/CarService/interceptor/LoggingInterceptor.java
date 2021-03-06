package com.nttdata.CarService.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LogManager.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.trace("Request-Method: " + request.getMethod());
        LOGGER.trace("Request-URL: " + request.getRequestURI());
        LOGGER.trace("Request-TYP: " + request.getContentType());
        LOGGER.trace("---HEADERS---");
        LOGGER.trace("Request-Headers-HOST: " + request.getHeader("host"));
        LOGGER.trace("Request-Headers-USER-AGENT: " + request.getHeader("user-agent"));
        LOGGER.trace("Request-Headers-ACCEPT: " + request.getHeader("accept"));

        String url = request.getRequestURI();

        switch (url) {
            case "/car/create":
                LOGGER.trace("Create car");
                break;
            case "/car/list":
                LOGGER.trace("List all Cars");
                break;
            case "/car/list{id}":
                LOGGER.trace("List selected Car");
                break;
            case "/car/edit":
                LOGGER.trace("Change or add properties of a car");
                break;
            case "/car/delete":
                LOGGER.trace("Delete a selected car");
                break;
            case "/car/deleteAll":
                LOGGER.trace("delete all cars");
                break;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.trace("Response-Status: " + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.trace("Request and Response is completed");

    }
}
