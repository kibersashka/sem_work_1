package com.technokratos.oris_semectrovka_01.service;

import com.technokratos.oris_semectrovka_01.DAO.AttachmentDAOImpl;
import com.technokratos.oris_semectrovka_01.DAO.methods.AttachmentDAO;
import com.technokratos.oris_semectrovka_01.entity.Attachments;
import com.technokratos.oris_semectrovka_01.entity.Task;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttachmentService {
    private AttachmentDAO attachmentDAO = new AttachmentDAOImpl();

    public void addAttachment(HttpServletRequest req, Date date_create) {

        Task task = new Task();
        task.setUsers_id((Long) req.getSession().getAttribute("id"));
        task.setTitle(req.getParameter("title"));
        task.setDescription(req.getParameter("description"));
        task.setDate_create(date_create);
        task.setDate_end(Date.valueOf(req.getParameter("date_end")));
        task.setPriority(Integer.parseInt(req.getParameter("priority")));
        task.setStatus(req.getParameter("status"));

        Long user_id = (Long) req.getSession().getAttribute("id");

        Attachments attachments = new Attachments();

        attachments.setTitle(req.getParameter("attachmentTitle"));
        attachments.setUrl(req.getParameter("attachment_url"));

        try {
            attachmentDAO.addAttachement(task, user_id, attachments);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Attachments> getAttachments(HttpServletRequest req) {
        Long user_id = (Long) req.getSession().getAttribute("id");

        if (req.getParameter("task_Id") == null) {
            return new ArrayList<>();
        }

        Long task_id = Long.valueOf(req.getParameter("task_Id"));
        try {
            return attachmentDAO.getAttachment(user_id, task_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
