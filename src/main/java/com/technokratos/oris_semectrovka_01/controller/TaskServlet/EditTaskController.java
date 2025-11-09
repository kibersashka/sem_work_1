package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.entity.Tag;
import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/edit-task")
public class EditTaskController extends HttpServlet {
    private TaskService taskService =  new TaskService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] tag_id = request.getParameterValues("tag_id[]");
        List<Tag> tags = new ArrayList<>();

        if (tag_id != null ) {
            for (int i = 0; i < tag_id.length; i++) {
                if (tag_id[i] != null && tag_id[i] != null) {
                    Tag tag = new Tag();
                    tag.setId(Long.valueOf(tag_id[i]));
                    tags.add(tag);
                }
            }
        }

        Task task = taskService.getTask(request);
        task.setTags(tags);

        request.setAttribute("task", task);
        request.getRequestDispatcher("/edit-task.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //нужно обноваить
        taskService.updateTask(request);
        response.sendRedirect("/oris_semectrovka_01_war_exploded/index");
    }
}
