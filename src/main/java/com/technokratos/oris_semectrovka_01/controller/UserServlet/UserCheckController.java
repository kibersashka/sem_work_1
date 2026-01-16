package com.technokratos.oris_semectrovka_01.controller.UserServlet;

import com.technokratos.oris_semectrovka_01.entity.User;
import com.technokratos.oris_semectrovka_01.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/usercheck")
public class UserCheckController extends HttpServlet {

    private UserService userService  = new UserService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        request.setAttribute("contextPath", request.getContextPath());

        if (session != null) {
            User formData = (User) session.getAttribute("formData");
            if (formData != null) {
                request.setAttribute("formData", formData);
                session.removeAttribute("formData");
            }

            String error = (String) session.getAttribute("error");
            if (error != null) {
                request.setAttribute("error", error);
                session.removeAttribute("error");
            }
        }

        request.getRequestDispatcher("/login.ftl").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resource = getString(request);
        request.setAttribute("contextPath", request.getContextPath());

        if (resource.equals("/login") && request.getSession().getAttribute("error") != null) {
            HttpSession session = request.getSession();
            User formData = new User();
            formData.setLogin(request.getParameter("login"));
            session.setAttribute("formData", formData);
        }

        response.sendRedirect(request.getContextPath() + resource);
    }

    private String getString(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String resource = "/login";

        if(session == null || session.getAttribute("user") == null) {
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            session = request.getSession();

            if(userService.loginUser(user)){
                session.setAttribute("id", user.getId());
                session.setAttribute("login", user.getLogin());
                resource = "/index";
            } else {
                request.getSession().setAttribute("error", "Не удалось найти пользователя");
                resource = "/login";
            }
        }
        return resource;
    }
}