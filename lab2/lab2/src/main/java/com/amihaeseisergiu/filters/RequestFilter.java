package com.amihaeseisergiu.filters;

import com.amihaeseisergiu.utils.Logger;
import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("RequestFilter for input.jps initialized");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String ipAddress = request.getRemoteAddr();
        Logger.getInstance().writeServerLog("Request received from IP " + ipAddress + ", at " + new Date().toString());

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("RequestFilter for input.jsp destroyed");
    }
}
