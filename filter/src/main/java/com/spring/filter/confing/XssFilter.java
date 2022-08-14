package com.spring.filter.confing;

import com.spring.filter.utils.StringUtils;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
@Slf4j
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("=========== Init Xss Filter ===========");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("=========== Request, Response ===========");

        XssServletRequestWrapper httpServletRequest = new XssServletRequestWrapper((HttpServletRequest) request);
        XssServletResponseWrapper httpServletResponse = new XssServletResponseWrapper((HttpServletResponse) response);

        chain.doFilter(httpServletRequest,httpServletResponse);


        byte[] byteArrayOutputStream = httpServletResponse.getByteArrayOutputStream(httpServletResponse);
        response.setContentLength(byteArrayOutputStream.length); // escape 하면서 달라진 길이를 재설정해줘야함, ex) &amp; -> &
        response.getOutputStream().write(byteArrayOutputStream);
        response.flushBuffer();

    }

    @Override
    public void destroy() {
        log.info("=========== Destroy Xss Filter ===========");
    }

}
