package com.technokratos.oris_semectrovka_01.service;

import com.technokratos.oris_semectrovka_01.DAO.TaskDAOImpl;
import com.technokratos.oris_semectrovka_01.entity.Attachments;
import com.technokratos.oris_semectrovka_01.entity.Tag;
import com.technokratos.oris_semectrovka_01.entity.Task;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private TaskDAOImpl taskDAO = new TaskDAOImpl();

    public String addTask(Task task, String[] attachmentsTitle, String[] attachmentsUsl, String[] tag_id) {

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
            taskDAO.addTask(task, attachmentsList,  tags);
            return null;
        } catch (SQLException e) {
           return "Не удалось добавить задачу!";
        }
    }

    public List<Task> getAllTasksForUser(Long id, Date date_create) {
        try {
            List<Task> res = taskDAO.getAllTasks(id, date_create);

            return res;
        } catch (SQLException e) {
            return null;
        }
    }

    public String deleteTask(Long id) {
        try {
            taskDAO.deleteTask(id);
            return null;
        } catch (SQLException e) {
            return "Не удалось добавить задачу!";
        }


    }

    public Task getTask(Long task_id, String[] tag_id ) {

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
        try {
           Task task = taskDAO.getTask(task_id).get();
           task.setTags(tags);
           return task;
        } catch (SQLException e) {
            return null;
        }
    }

    public void updateTask(Long task_id, Task task, String[] attachmentsTitle, String[] attachmentsUsl, String[] tag_id) {


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
