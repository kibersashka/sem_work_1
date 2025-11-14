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

@WebServlet("/redactor")
public class RedactorAccountController extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/redactor.ftl").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = getUser(request, session);


        String result = userService.changeData(user);

        if (result != null) {
            request.setAttribute("error", result);
        }
        request.getRequestDispatcher("/redactor.ftl").forward(request, response);
    }

    private static User getUser(HttpServletRequest request, HttpSession session) {
        User user = new User();

        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setLogin((String) session.getAttribute("login"));
        return user;
    }
}
