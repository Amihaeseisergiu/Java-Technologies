package com.amihaeseisergiu.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ResponseDecorator implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        String word = request.getParameter("word");
        String definition = request.getParameter("definition");
        
        boolean checkParams = word != null && !word.isEmpty() && definition != null && !definition.isEmpty();
        
        if(checkParams)
        {
            SimpleResponseWrapper wrapper = new SimpleResponseWrapper((HttpServletResponse) response);
            wrapper.getWriter().write("<p> Prelude </p>");

            chain.doFilter(request, wrapper);

            String content = wrapper.toString();
            content += "<p> Coda </p>";

            PrintWriter out = response.getWriter();
            out.write(content);
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ResponseDecorator initialized");
    }

    @Override
    public void destroy() {
        System.out.println("ResponseDecorator destroyed");
    }
}
