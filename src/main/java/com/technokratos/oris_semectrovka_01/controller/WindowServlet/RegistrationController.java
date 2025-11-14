package com.technokratos.oris_semectrovka_01.controller.WindowServlet;

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
        request.getRequestDispatcher("/registration.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request);

        if (ValidateService.getValidPassword(user) == null) {
            request.setAttribute("error", ValidateService.getValidPassword(user));
            request.getRequestDispatcher("/registration.ftl").forward(request, response);
        }


        Optional<User> res = userService.registrationUser(user);

        if (res.isEmpty()) {
            request.setAttribute("error", "Пользователь с таким логином уже существует!");
            request.getRequestDispatcher("/registration.ftl").forward(request, response);
        }

        HttpSession session = request.getSession();

        session.setAttribute("id", user.getId());

        session.setAttribute("login", user.getLogin());

        request.setAttribute("user", user.getLogin());

        request.getRequestDispatcher("/index.ftl").forward(request, response);
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
