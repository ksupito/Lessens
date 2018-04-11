package com.example.project.filters;

import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
@WebFilter("/*")
public class FilterAutoriz implements Filter {
    @Autowired
    private UserService userService;
    private List<String> pathFilters = Arrays.asList(new String[]{"input", "input.jsp", "result", "result.jsp"});

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        String path = StringUtils.substringAfterLast(url, "/");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        if (!pathFilters.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        try {
            if (userService.checkUser(login, password) && session.getAttribute("authorized") == null) {
                session.setAttribute("authorized", true);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            if (!userService.checkUser(login, password) && (login != null || password != null)) {
                response.sendRedirect("/login?error=true");
                return;
            }
            if (session.getAttribute("authorized") != null) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        response.sendError(403, "Access denied");
    }

    public void destroy() {
    }
}

