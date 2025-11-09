package com.technokratos.oris_semectrovka_01.service;

import com.technokratos.oris_semectrovka_01.DAO.TaskDAOImpl;
import com.technokratos.oris_semectrovka_01.connection.DBConnection;
import com.technokratos.oris_semectrovka_01.entity.Attachments;
import com.technokratos.oris_semectrovka_01.entity.Tag;
import com.technokratos.oris_semectrovka_01.entity.Task;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private TaskDAOImpl taskDAO = new TaskDAOImpl();

    public void addTask(HttpServletRequest req, Date date_create) {
        Task task = new Task();

        task.setUsers_id((Long) req.getSession().getAttribute("id"));

        task.setTitle(req.getParameter("title"));

        task.setDescription(req.getParameter("description"));

        task.setDate_create(date_create);
        System.out.println("Add task day: " + date_create);

        task.setDate_end(Date.valueOf(req.getParameter("date_end")));
        System.out.println("Add task day: " + date_create);

        task.setPriority(Integer.valueOf(req.getParameter("priority")));
        System.out.println("Add task day: " + date_create);

        task.setStatus(req.getParameter("status"));
        System.out.println(task);


        //attachments.setTitle(req.getParameter("attachmentTitle"));
        String[] attachmentsTitle = req.getParameterValues("attachmentTitle[]");
        String[] attachmentsUsl = req.getParameterValues("attachment_url[]");

        List<Attachments> attachmentsList = new ArrayList<>();

        if (attachmentsTitle != null && attachmentsUsl != null) {
            for (int i = 0; i < attachmentsTitle.length; i++) {
                if (attachmentsUsl[i] != null
                        && attachmentsTitle[i] != null
                        && !attachmentsTitle[i].trim().isEmpty()
                        && !attachmentsUsl[i].trim().isEmpty()) {
                    Attachments attachments = new Attachments();
                    attachments.setTitle(attachmentsTitle[i]);
                    attachments.setUrl(attachmentsUsl[i]);
                    attachmentsList.add(attachments);
                }
            }
        }
        task.setAttachments(attachmentsList);
        System.out.println(attachmentsList);

        String[] tag_id = req.getParameterValues("tag_id[]");


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
        task.setTags(tags);

        System.out.println();
        System.out.println("Add task day: " + date_create);
        System.out.println();

        try {
            taskDAO.addTask(task, attachmentsList,  tags);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllTasksForUser(HttpServletRequest req, Date date_create) {
        try {
            System.out.println((Long) req.getSession().getAttribute("id"));
            List<Task> res = taskDAO.getTasksForUser((Long) req.getSession().getAttribute("id"), date_create);

            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTask(HttpServletRequest req) {
        Long task_id = Long.valueOf(req.getParameter("task_id"));
        try {
            taskDAO.deleteTask(task_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Task getTask(HttpServletRequest req) {
        Long task_id = Long.valueOf(req.getParameter("task_id"));



        try {
           return taskDAO.getTask(task_id).get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateTask(HttpServletRequest req) {
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

        List<Attachments> attachmentsList = new ArrayList<>();

        if (attachmentsTitle != null && attachmentsUsl != null) {
            for (int i = 0; i < attachmentsTitle.length; i++) {
                if (attachmentsUsl[i] != null
                        && attachmentsTitle[i] != null
                        && !attachmentsTitle[i].trim().isEmpty()
                        && !attachmentsUsl[i].trim().isEmpty()) {
                    Attachments attachments = new Attachments();
                    attachments.setTitle(attachmentsTitle[i]);
                    attachments.setUrl(attachmentsUsl[i]);
                    attachmentsList.add(attachments);
                }
            }
        }
        task.setAttachments(attachmentsList);
        String[] tag_id = req.getParameterValues("tag_id[]");


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
        task.setTags(tags);

        try {
            taskDAO.updateTask(task, tags);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
