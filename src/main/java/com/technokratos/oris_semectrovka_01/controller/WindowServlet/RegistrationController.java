package com.technokratos.oris_semectrovka_01.controller.WindowServlet;

import com.technokratos.oris_semectrovka_01.entity.User;
import com.technokratos.oris_semectrovka_01.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = userService.registrationUser(request);

        HttpSession session = request.getSession();

        session.setAttribute("login", user.getLogin());

        request.setAttribute("user", user.getLogin());

        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }
}
