package com.spring.filter.confing;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@Slf4j
public class XssRequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }


    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = values[i];
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String name) {
        String value =  super.getParameter(name);

        if (value == null) {
            return null;
        }

        return value;
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        return super.getInputStream();
    }

    
}
