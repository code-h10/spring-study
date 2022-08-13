package com.spring.filter.confing;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class XssResponseWrapper extends HttpServletResponseWrapper {
    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public XssResponseWrapper(HttpServletResponse response) {
        super(response);
    }
}
