package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-task")
public class DeleteTaskController extends HttpServlet {
    private TaskService taskService = new TaskService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = taskService.deleteTask(Long.valueOf(request.getParameter("task_id")));
        if (result != null) {
            request.getSession().setAttribute("error", result);
        }
        response.sendRedirect("/oris_semectrovka_01_war_exploded/index");
    }
}
