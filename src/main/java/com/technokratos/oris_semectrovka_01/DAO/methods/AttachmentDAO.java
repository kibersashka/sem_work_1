package com.technokratos.oris_semectrovka_01.DAO.methods;

import com.technokratos.oris_semectrovka_01.entity.Attachments;
import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AttachmentDAO {
    void addAttachement(Task task, Long user_id, Attachments attachments) throws SQLException;
    void updateAttachment(Attachments attachment) throws SQLException;
    void deleteAttachment(Attachments attachment) throws SQLException;
    List<Attachments> getAttachment(Long user_id, Long task_id) throws SQLException;
    //List<Attachments> getAllAttachmentsForUser(User user, Task task) throws SQLException;
}
