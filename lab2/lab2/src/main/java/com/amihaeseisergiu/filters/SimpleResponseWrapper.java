package com.amihaeseisergiu.filters;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class SimpleResponseWrapper extends HttpServletResponseWrapper {
    
    private final StringWriter output;
    
    public SimpleResponseWrapper(HttpServletResponse response)
    {
        super(response);
        output = new StringWriter();
    }
    
    @Override
    public PrintWriter getWriter()
    {
        return new PrintWriter(output);
    }
    
    @Override
    public String toString()
    {
        return output.toString();
    }
}
