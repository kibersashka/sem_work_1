package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.controller.TaskServlet.Util.GenerateDateUtil;
import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/showtaskOnDate")
public class ShowtaskOnDate extends HttpServlet {
    private TaskService taskService = new TaskService();

    public void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
        Date date = GenerateDateUtil.generateData(request);

        List<Task> tasks = taskService.getAllTasksForUser((Long) request.getSession().getAttribute("id"), date);
        if (tasks == null) {
            request.setAttribute("error", "Не удалось найти задачу");
        } else {
            request.setAttribute("tasks", tasks);
        }
        request.getRequestDispatcher("/showtaskOnDate.ftl").forward(request, res);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
