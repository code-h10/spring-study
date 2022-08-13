package com.spring.filter.confing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("=========== Init Xss Filter ===========");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("=========== Request, Response ===========");

        log.info("=========================================");
        log.info(request.getLocalName());
        log.info(request.getReader().toString());
        log.info(request.getProtocol());
        log.info(request.getLocalAddr());
        log.info("=========================================");

        XssRequestWrapper xssRequestWrapper = new XssRequestWrapper((HttpServletRequest) request);
//        XssResponseWrapper xssResponseWrapper = new XssResponseWrapper((HttpServletResponse) request);

        chain.doFilter(xssRequestWrapper,response);
    }

    @Override
    public void destroy() {
        log.info("=========== Destroy Xss Filter ===========");
    }
}
