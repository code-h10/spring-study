package com.spring.filter.confing;

import com.spring.filter.utils.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.springframework.util.StreamUtils;

public class XssServletResponseWrapper extends HttpServletResponseWrapper {

    ByteArrayOutputStream byteArrayOutputStream;
    XssServletOutputStream xssServletOutputStream;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public XssServletResponseWrapper(HttpServletResponse response) throws IOException {
        // TODO 작업
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (xssServletOutputStream == null) {
            xssServletOutputStream = new XssServletOutputStream(byteArrayOutputStream);
        }
        return xssServletOutputStream;
    }


    public byte[] getByteArrayOutputStream(HttpServletResponse httpServletResponse) {
        if(hasContentDisposition(httpServletResponse)) {
            return byteArrayOutputStream.toByteArray();
        } else {
            return StringUtils.unescape(byteArrayOutputStream.toByteArray());
        }
    }

    private boolean hasContentDisposition(HttpServletResponse httpServletResponse) {
        return httpServletResponse.containsHeader("Content-Disposition");
    }


    public class XssServletOutputStream extends ServletOutputStream {

        private final DataOutputStream outputStream;

        public XssServletOutputStream(OutputStream output) {
            this.outputStream = new DataOutputStream(output);
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }
    }
}
