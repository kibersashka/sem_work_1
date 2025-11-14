package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.service.TaskService;
import com.technokratos.oris_semectrovka_01.service.ValidateService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/add-task")
public class AddTaskServlet extends HttpServlet {


    private TaskService taskService = new TaskService();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("dayPick", request.getParameter("day"));
        request.getSession().setAttribute("monthPick", request.getParameter("month"));
        request.getSession().setAttribute("yearPick", request.getParameter("year"));
        request.getRequestDispatcher("/add-task.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Task task = getTask(request);

        request.getSession().setAttribute("error", ValidateService.getValidDate(task));

        String[] attachmentsTitle = request.getParameterValues("attachmentTitle[]");
        String[] attachmentsUsl = request.getParameterValues("attachment_url[]");
        String[] tag_id = request.getParameterValues("tag_id[]");
        String result = taskService.addTask(task, attachmentsTitle, attachmentsUsl, tag_id);


        if (result != null) {
            request.getSession().setAttribute("error", result);
        }
        response.sendRedirect(request.getContextPath() + "/showtask");

    }


    private Task getTask(HttpServletRequest request) {
        int year = Integer.parseInt(request.getSession().getAttribute("yearPick").toString());

        int month = Integer.parseInt(request.getSession().getAttribute("monthPick").toString());

        int day = Integer.parseInt(request.getSession().getAttribute("dayPick").toString());

        Task task = new Task();


        task.setUsers_id((Long) request.getSession().getAttribute("id"));

        task.setTitle(request.getParameter("title"));

        task.setDescription(request.getParameter("description"));

        task.setDate_create( new Date(year - 1900, month - 1, day));

        task.setStatus(request.getParameter("status"));

        task.setDate_end(Date.valueOf(request.getParameter("date_end")));

        task.setPriority(Integer.valueOf(request.getParameter("priority")));




        return task;
    }


}
