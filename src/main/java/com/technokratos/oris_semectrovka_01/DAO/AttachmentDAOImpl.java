package com.technokratos.oris_semectrovka_01.DAO;

import com.technokratos.oris_semectrovka_01.DAO.methods.AttachmentDAO;
import com.technokratos.oris_semectrovka_01.DAO.methods.TaskDAO;
import com.technokratos.oris_semectrovka_01.connection.DBConnection;
import com.technokratos.oris_semectrovka_01.entity.Attachments;
import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttachmentDAOImpl implements AttachmentDAO {
    @Override
    public void addAttachement(Task task, Long user_id, Attachments attachments) throws SQLException {
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);

        String sql0 = "select id from task where users_id = ? and title = ?";
        PreparedStatement preparedStatement0 = connection.prepareStatement(sql0);
        preparedStatement0.setLong(1, user_id);
        preparedStatement0.setString(2, task.getTitle());
        ResultSet resultSet0 = preparedStatement0.executeQuery();
        Long task_id = null;
        if (resultSet0.next()) {
            task_id = resultSet0.getLong("id");
        } else {
            throw new SQLException();
        }
        preparedStatement0.close();
        resultSet0.close();


        //получить айдишник нового вложения
        String sql = "select id from nextval('seq_attachment') as id";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        Long attachment_id = null;
        if (resultSet.next()) {
            attachment_id = resultSet.getLong("id");
        } else {
            throw new SQLException();
        }
        preparedStatement.close();
        resultSet.close();

        //вставить в таблицу связи
        String sql1 = "insert into task_attachment values(?,?)";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        preparedStatement1.setLong(1, task_id);
        preparedStatement1.setLong(2, attachment_id);
        int res1 = preparedStatement1.executeUpdate();
        preparedStatement1.close();

        //вставить в таблицу вложений
        String sql2 = "insert into attachment values(?, ?, ?, ?)";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setLong(1, attachment_id);
        preparedStatement2.setString(2, attachments.getUrl());
        preparedStatement2.setString(3, attachments.getTitle());
        preparedStatement2.setLong(4, user_id);
        int res2 = preparedStatement2.executeUpdate();
        preparedStatement2.close();


        connection.commit();
        connection.close();
    }

    @Override
    public void updateAttachment(Attachments attachment) throws SQLException {
        String sql = "update task_attachment set title = ?, url = ? where id = ?";
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, attachment.getTitle());
        preparedStatement.setString(2, attachment.getUrl());
        preparedStatement.setLong(3, attachment.getId());
        preparedStatement.executeUpdate();
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.commit();
        connection.close();
    }

    @Override
    public void deleteAttachment(Attachments attachment) throws SQLException {
        String sql = "delete from attachment where id = ?";
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, attachment.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.commit();
        connection.close();
    }

    @Override
    public List<Attachments> getAttachment(Long user_id, Long task_id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);


        String sql = "select title, url\n" +
                "from attachment\n" +
                "join public.users u on attachment.user_id = u.id\n" +
                "join task_attachment ta on attachment.id = ta.attachment\n" +
                "where u.id = ? and ta.task_id = ?;";


        ArrayList<Attachments> attachments = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, user_id);
        preparedStatement.setLong(2, task_id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String url = resultSet.getString("url");
            Attachments attachment = new Attachments();
            attachment.setTitle(title);
            attachment.setUrl(url);
            attachments.add(attachment);
        }
        preparedStatement.close();
        resultSet.close();
        connection.commit();
        connection.close();
        return attachments;

    }



}
