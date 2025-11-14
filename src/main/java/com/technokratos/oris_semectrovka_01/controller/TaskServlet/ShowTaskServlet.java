package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.controller.TaskServlet.Util.GenerateDateUtil;
import com.technokratos.oris_semectrovka_01.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet("/showtask")
public class ShowTaskServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenerateDateUtil.generateData(request);

        request.setAttribute("error", request.getSession().getAttribute("error"));
        request.getSession().removeAttribute("error");

        request.getRequestDispatcher("/showtask.ftl").forward(request, response);
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/showtaskOnDate.ftl").forward(request, response);
    }
}
