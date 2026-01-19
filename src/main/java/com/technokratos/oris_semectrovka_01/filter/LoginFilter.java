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

        if (((HttpServletRequest) request).getRequestURI().startsWith(((HttpServletRequest) request).getContextPath() + "/static/")) {
            chain.doFilter(request, response);
            return;
        }
        if (! checkE(((HttpServletRequest) request).getServletPath()) && (session == null ||
                session.getAttribute("login") == null)) {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");

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
