package com.technokratos.oris_semectrovka_01.controller.WindowServlet;

import com.technokratos.oris_semectrovka_01.controller.TaskServlet.Util.GenerateDateUtil;
import com.technokratos.oris_semectrovka_01.entity.Task;
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
import java.util.List;

@WebServlet("/index")
public class IndexController extends HttpServlet {

    private TaskService taskService =  new TaskService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("error") != null) {
            request.setAttribute("error", request.getSession().getAttribute("error"));
            request.getSession().removeAttribute("error");
        }
        int year = LocalDate.now(ZoneId.systemDefault()).getYear();

        int month =  LocalDate.now().getMonthValue() ;
        int day = LocalDate.now().getDayOfMonth();
        if (month < 1) { month = 12; year--; }
        if (month > 12) { month = 1; year++; }

        List<Task> tasks = taskService.getAllTasksForUser((Long) request.getSession().getAttribute("id"), GenerateDateUtil.generateData(request));
        System.out.println(tasks);
        if (tasks == null) {
            request.setAttribute("error", "Не удалось найти задачи!");
        } else {
            request.setAttribute("tasks", tasks);
        }

        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
