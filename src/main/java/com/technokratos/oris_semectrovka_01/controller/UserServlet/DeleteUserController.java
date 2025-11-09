package com.technokratos.oris_semectrovka_01.controller.UserServlet;

import com.technokratos.oris_semectrovka_01.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserController extends HttpServlet {
    private UserService userService = new UserService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.delete(request);
        request.getRequestDispatcher("/title-window.ftl").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
