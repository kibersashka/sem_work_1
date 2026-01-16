package com.technokratos.oris_semectrovka_01.controller.UserServlet;

import com.technokratos.oris_semectrovka_01.entity.User;
import com.technokratos.oris_semectrovka_01.service.UserService;
import com.technokratos.oris_semectrovka_01.service.ValidateService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() == null) {
            request.getSession().invalidate();
        }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1

        HttpSession session = request.getSession(false);
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
        request.setAttribute("contextPath", request.getContextPath());


        request.getRequestDispatcher("/registration.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request);
        request.setAttribute("contextPath", request.getContextPath());
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1

        HttpSession session = request.getSession();

        String error = ValidateService.getValidPassword(user);
        if (error != null) {
            session.setAttribute("error", error);
            session.setAttribute("formData", user);
            response.sendRedirect(request.getContextPath() + "/registration");
            return;
        }

        Optional<User> res = userService.registrationUser(user);

        if (res.isEmpty()) {
            session.setAttribute("error", "Пользователь с таким логином уже существует!");
            session.setAttribute("formData", user);
            response.sendRedirect(request.getContextPath() + "/registration");
            return;
        }

        session.setAttribute("id", user.getId());
        session.setAttribute("login", user.getLogin());
        response.sendRedirect(request.getContextPath() + "/index");
    }

    private static User getUser(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        return user;
    }
}