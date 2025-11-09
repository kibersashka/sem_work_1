package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.service.AttachmentService;
import com.technokratos.oris_semectrovka_01.service.TaskService;
import com.technokratos.oris_semectrovka_01.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/add-task")
public class AddTaskServlet extends HttpServlet {

    private UserService userService = new UserService();
    private TaskService taskService = new TaskService();
    private AttachmentService attachmentService = new AttachmentService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add-task.ftl").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int year = Integer.parseInt(request.getSession().getAttribute("yearPick").toString());

        int month = Integer.parseInt(request.getSession().getAttribute("monthPick").toString());

        int day = Integer.parseInt(request.getSession().getAttribute("dayPick").toString());

        //System.out.println(request.getParameter("title"));

        System.out.println("tag" + request.getParameter("tag_id"));
        taskService.addTask(request, new Date(year - 1900, month - 1, day));


        response.sendRedirect(request.getContextPath() + "/showtask");

    }
}
