package com.spring.filter.confing;

import com.spring.filter.utils.StringUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

@Slf4j
public class XssServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] rawData;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream inputStream = request.getInputStream();
        this.rawData = StringUtils.escape(StreamUtils.copyToByteArray(inputStream));
    }


    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);

        if (values == null) {
            return null;
        }

        int length = values.length;
        String[] encodedValues = new String[length];
        for (int i = 0; i < length; i++) {
            encodedValues[i] = StringUtils.escape(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String name) {
        String value =  super.getParameter(name);

        if (value == null) {
            return null;
        }

        return StringUtils.escape(value);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new XssServletInputStream(this.rawData);
    }

    private class XssServletInputStream extends ServletInputStream {

        private InputStream inputStream;

        public XssServletInputStream(byte[] rawData) {
            this.inputStream = new ByteArrayInputStream(rawData);
        }

        @Override
        public boolean isFinished() {

            boolean isFinished = false;
            try {
                isFinished = inputStream.available() == 0;
            } catch(IOException e) {
                log.error("Servlet Input Stream Finished Error",e);
            }
            return  isFinished;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }
    }



    
}
