package com.technokratos.oris_semectrovka_01.DAO.methods;

import com.technokratos.oris_semectrovka_01.entity.Attachments;
import com.technokratos.oris_semectrovka_01.entity.Tag;
import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TaskDAO {
    List<Tag> getTags(Long task_id) throws SQLException;
    List<Attachments> getAttachments(Long user_id, Long task_id) throws SQLException;
    List<Task> getAllTasks(Long user_id, Date date_create) throws SQLException;
    Optional<Task> getTask(Long id) throws SQLException;
    void deleteTask(Long task_id) throws SQLException;
    void updateTask(Task task, List<Tag> tags) throws SQLException;
    void addTask(Task task, List<Attachments> attachment, List<Tag> tags) throws SQLException;

}
