package com.therapy.therapy.test;

import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class Filter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Remote Host By Tsega="+request.getRemoteHost());
        chain.doFilter(request,response);
        request.setAttribute("body","My Body");
    }
}
