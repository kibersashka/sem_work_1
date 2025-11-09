package com.technokratos.oris_semectrovka_01.controller.WindowServlet;

import com.technokratos.oris_semectrovka_01.service.TaskService;
import com.technokratos.oris_semectrovka_01.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@WebServlet("/index")
public class IndexController extends HttpServlet {
    private TaskService taskService =  new TaskService();
    private UserService userService = new UserService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int year = LocalDate.now(ZoneId.systemDefault()).getYear();
        System.out.println("тут");

        int month =  LocalDate.now().getMonthValue() ;
        int day = LocalDate.now().getDayOfMonth();
        if (month < 1) { month = 12; year--; }
        if (month > 12) { month = 1; year++; }

        request.setAttribute("tasks", taskService.getAllTasksForUser(request, new Date(year - 1900, month - 1, day)));

        System.out.println(request.getAttribute("tasks"));
        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
