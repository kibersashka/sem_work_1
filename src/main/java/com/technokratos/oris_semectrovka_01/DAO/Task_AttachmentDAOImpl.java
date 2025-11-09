package com.technokratos.oris_semectrovka_01.DAO;

import com.technokratos.oris_semectrovka_01.DAO.methods.Task_Attachment;
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

public class Task_AttachmentDAOImpl implements Task_Attachment {

    @Override
    public List<Attachments> getAllAttachmentsForUser(User user, Task task) throws SQLException {
        String sql = "select url,\n" +
                "       title\n" +
                "from attachment\n" +
                "join users u on attachment.user_id = u.id\n" +
                "join public.task t on u.id = t.users_id\n" +
                "where u.login = ? and \n" +
                "      u.password = ? and\n" +
                "      t.id = ?;";
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Attachments> attachments = new ArrayList<>();
        while (resultSet.next()) {
            Attachments attachment = new Attachments();
            attachment.setUrl(resultSet.getString("url"));
            attachment.setTitle(resultSet.getString("title"));
            attachments.add(attachment);
        }
        preparedStatement.close();
        resultSet.close();
        connection.commit();
        connection.close();

        return attachments;
    }


}
