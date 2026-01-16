package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.controller.TaskServlet.Util.GenerateDateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showtask")
public class ShowTaskServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenerateDateUtil.generateData(request);
        request.setAttribute("contextPath", request.getContextPath());

        request.setAttribute("error", request.getSession().getAttribute("error"));
        request.getSession().removeAttribute("error");
        request.getRequestDispatcher("/showtask.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/showtaskOnDate");
    }
}