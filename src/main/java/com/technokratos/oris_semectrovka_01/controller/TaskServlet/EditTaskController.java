package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.entity.Tag;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/edit-task")
public class EditTaskController extends HttpServlet {
    private TaskService taskService =  new TaskService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] tag_id = request.getParameterValues("tag_id[]");

        Task task = taskService.getTask(Long.valueOf(request.getParameter("task_id")), tag_id);

        if (task == null) {
            request.setAttribute("error", "Не удалось найти задачу");
        }

        request.setAttribute("task", task);
        request.getRequestDispatcher("/edit-task.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //нужно обноваить
        Long task_id = Long.valueOf(req.getParameter("task_id"));
        System.out.println("Update task id: " + task_id);


        Task task = new Task();

        task.setId(task_id);
        task.setUsers_id((Long) req.getSession().getAttribute("id"));
        task.setTitle(req.getParameter("title"));
        task.setDescription(req.getParameter("description"));

        task.setDate_end(Date.valueOf(req.getParameter("date_end")));
        task.setStatus(req.getParameter("status"));
        task.setPriority(Integer.valueOf(req.getParameter("priority")));

        String[] attachmentsTitle = req.getParameterValues("attachmentTitle[]");
        String[] attachmentsUsl = req.getParameterValues("attachment_url[]");
        String[] tag_id = req.getParameterValues("tag_id[]");
        Task task2 = taskService.getTask(Long.valueOf(req.getParameter("task_id")), tag_id);
        task.setDate_create(task2.getDate_create());

        taskService.updateTask(task_id, task, attachmentsTitle, attachmentsUsl, tag_id);

        req.getSession().setAttribute("error", ValidateService.getValidDate(task));

        response.sendRedirect(req.getContextPath() + "/showtask");
    }
}
