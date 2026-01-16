package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.service.TaskService;
import com.technokratos.oris_semectrovka_01.service.ValidateService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/edit-task")
public class EditTaskController extends HttpServlet {
    private TaskService taskService =  new TaskService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Task formData = (Task) session.getAttribute("formData");
            if (formData != null) {
                request.setAttribute("task", formData);
                session.removeAttribute("formData");
            }

            String error = (String) session.getAttribute("error");
            if (error != null) {
                request.setAttribute("error", error);
                session.removeAttribute("error");
            }
        }

        if (request.getAttribute("task") == null) {
            String[] tag_id = request.getParameterValues("tag_id[]");
            Task task = taskService.getTask(Long.valueOf(request.getParameter("task_id")), tag_id);
            if (task == null) {
                request.setAttribute("error", "Не удалось найти задачу");
            }
            request.setAttribute("task", task);
        }
        request.setAttribute("contextPath", request.getContextPath());


        request.getRequestDispatcher("/edit-task.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Long task_id = Long.valueOf(req.getParameter("task_id"));
        req.setAttribute("contextPath", req.getContextPath());

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

        String res = ValidateService.getValidDate(task);
        if ((res) != null) {
            HttpSession session = req.getSession();
            session.setAttribute("error", res);
            session.setAttribute("formData", task);
            response.sendRedirect(req.getContextPath() + "/edit-task?task_id=" + task_id);
            return;
        }

        taskService.updateTask(task_id, task, attachmentsTitle, attachmentsUsl, tag_id);
        response.sendRedirect(req.getContextPath() + "/showtask");
    }
}