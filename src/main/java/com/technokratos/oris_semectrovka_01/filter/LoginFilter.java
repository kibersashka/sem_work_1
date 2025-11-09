package com.technokratos.oris_semectrovka_01.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 *
 *
 *
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (! checkE(((HttpServletRequest) request).getServletPath()) && (session == null ||
                session.getAttribute("login") == null)) {
            System.out.println();

            ((HttpServletResponse) response).sendRedirect("/oris_semectrovka_01_war_exploded/login");

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean checkE (String res) {
        return res.contains("/title-window") || res.contains("/login") || res.contains("/registration") || res.contains("/usercheck") || res.contains("/css") ;
    }
}
