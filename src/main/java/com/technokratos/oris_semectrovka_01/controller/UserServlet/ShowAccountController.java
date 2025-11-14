package com.technokratos.oris_semectrovka_01.controller.UserServlet;


import com.technokratos.oris_semectrovka_01.entity.User;
import com.technokratos.oris_semectrovka_01.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/show-account")
public class ShowAccountController extends HttpServlet {
    private UserService userService =  new UserService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = userService.getUser((Long) request.getSession().getAttribute("id")).get();
        if (user == null) {
            request.setAttribute("error", "Не удалось найти пользователя");
        }
        request.setAttribute("user", user);

        request.getRequestDispatcher("show-account.ftl").forward(request, response);
    }

}
