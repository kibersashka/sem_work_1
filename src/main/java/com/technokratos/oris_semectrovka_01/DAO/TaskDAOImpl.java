package com.technokratos.oris_semectrovka_01.DAO;

import com.technokratos.oris_semectrovka_01.DAO.methods.TaskDAO;
import com.technokratos.oris_semectrovka_01.connection.DBConnection;
import com.technokratos.oris_semectrovka_01.entity.Attachments;
import com.technokratos.oris_semectrovka_01.entity.Tag;
import com.technokratos.oris_semectrovka_01.entity.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TaskDAOImpl implements TaskDAO {


    public void addTask(Task task, List<Attachments> attachment, List<Tag> tags) throws SQLException {
            //получить айдишщник задачи из очереди
            String sql = "select id from nextval('seq_task') as id";
            Connection connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            Long task_id = null;
            if (resultSet.next()) {
                task_id = resultSet.getLong("id");
            } else {
                throw new SQLException();
            }
            preparedStatement.close();
            resultSet.close();

            //вставитьь новую задачу - то что ожидается от метода
            String sql1 = "insert into task values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);

            preparedStatement1.setLong(1, task_id);
            preparedStatement1.setLong(2, task.getUsers_id());
            preparedStatement1.setString(3, task.getTitle());
            preparedStatement1.setString(4, task.getDescription());
            preparedStatement1.setDate(5, (Date) task.getDate_create());
            preparedStatement1.setDate(6, (Date) task.getDate_end());
            preparedStatement1.setInt(7, task.getPriority());
            preparedStatement1.setString(8,task.getStatus());

            int res1 = preparedStatement1.executeUpdate();
            preparedStatement1.close();

            for (Attachments attachments : attachment) {

                //получиться айдишник вложения из последовательности
                String sql2 = "select id from nextval('seq_attachment') as id";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                Long attachment_id = null;
                if (resultSet2.next()) {
                    attachment_id = resultSet2.getLong("id");
                } else {
                    throw new SQLException();
                }
                preparedStatement2.close();
                resultSet2.close();

                //вставить в таблицу свзять задачу - вложения
                String sql4 = "insert into task_attachment values(?, ?)";
                PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
                preparedStatement4.setLong(1, task_id);
                preparedStatement4.setLong(2, attachment_id);
                int res4 = preparedStatement4.executeUpdate();
                preparedStatement4.close();

                String sql5 = "insert into attachment values(?, ?, ?, ?)";
                preparedStatement1 = connection.prepareStatement(sql5);
                System.out.println("Adding attachment with id " + attachment_id);
                preparedStatement1.setLong(1, attachment_id);
                preparedStatement1.setString(2, attachments.getUrl());
                preparedStatement1.setLong(3, task.getUsers_id());
                preparedStatement1.setString(4,attachments.getTitle());



                int res5 = preparedStatement1.executeUpdate();
                preparedStatement1.close();
                System.out.println();
                System.out.println("вставка в задачу и вложения " + res4 +" " + res5);
                System.out.println();
            }

            for (Tag tag : tags) {
                //редактирование тега

                    String sql6 = "insert into task_tag values(?, ?)";
                    PreparedStatement preparedStatement3 = connection.prepareStatement(sql6);
                    preparedStatement3.setLong(1, task_id);

                    preparedStatement3.setLong(2, tag.getId());
                    int res6 = preparedStatement3.executeUpdate();
                    preparedStatement3.close();

                    System.out.println("вставка тега " + res6);

            }

            connection.commit();
            connection.close();

    }

    //обновление задачи ее полей!! не вложения

    public void updateTask(Task task, List<Tag> tags) throws SQLException {
        String sql = "update task set title = ?, description = ?,date_end = ?, priority = ?, status = ? where id = ?";

        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, task.getTitle());
        preparedStatement.setString(2, task.getDescription());

        preparedStatement.setDate(3, (Date) task.getDate_end());
        preparedStatement.setInt(4, task.getPriority());
        preparedStatement.setString(5, task.getStatus());
        preparedStatement.setLong(6, task.getId());

        int res1 = preparedStatement.executeUpdate();
        preparedStatement.close();

        for (Attachments attachments : task.getAttachments()) {

            //получиться айдишник вложения из последовательности
            String sql2 = "select id from nextval('seq_attachment') as id";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            Long attachment_id = null;
            if (resultSet2.next()) {
                attachment_id = resultSet2.getLong("id");
            } else {
                throw new SQLException();
            }
            preparedStatement2.close();
            resultSet2.close();

            //вставить в таблицу свзять задачу - вложения
            String sql4 = "insert into task_attachment values(?, ?)";
            PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
            preparedStatement4.setLong(1, task.getId());
            preparedStatement4.setLong(2, attachment_id);
            int res4 = preparedStatement4.executeUpdate();
            preparedStatement4.close();

            String sql5 = "insert into attachment values(?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql5);
            System.out.println("Adding attachment with id " + attachment_id);
            preparedStatement1.setLong(1, attachment_id);
            preparedStatement1.setString(2, attachments.getUrl());
            preparedStatement1.setLong(3, task.getUsers_id());
            preparedStatement1.setString(4,attachments.getTitle());

            int res5 = preparedStatement1.executeUpdate();
            preparedStatement1.close();
        }


        for (Tag tag : tags) {
            String sql5 = "select task_id, tag_id from task_tag where task_id = ? and tag_id = ?";
            PreparedStatement preparedStatement3 = connection.prepareStatement(sql5);
            preparedStatement3.setLong(1, task.getId());
            preparedStatement3.setLong(2, tag.getId());
            ResultSet resultSet3 = preparedStatement3.executeQuery();
            System.out.println(tag.getId());

            if (resultSet3.next() && resultSet3.getLong("task_id") == task.getId() && resultSet3.getLong("tag_id") == tag.getId()) {
                


            } else {
                System.out.println("FALSE++++++++++++++++++++");
                String sql6 = "insert into task_tag values(?, ?)";
                System.out.println("oo");
                PreparedStatement preparedStatement6 = connection.prepareStatement(sql6);
                preparedStatement6.setLong(1, task.getId());
                System.out.println("now");

                preparedStatement6.setLong(2, tag.getId());
                int res6 = preparedStatement6.executeUpdate();
                System.out.println(res6);
                preparedStatement3.close();
            }
            resultSet3.close();

        }

        connection.commit();
        connection.close();
    }
    //удаление ТОлько задачи

    public void deleteTask(Long task_id) throws SQLException {
        String sql = "delete from task where id = ?";
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, task_id);
        int res1 = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.commit();
        connection.close();
    }

    //вернуть задачу по айди

    public Optional<Task> getTask(Long id) throws SQLException {
        String sql = "select * from task where id = ?";
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Task task = new Task();

            task.setId(resultSet.getLong("id"));
            task.setUsers_id(resultSet.getLong("users_id"));
            task.setTitle(resultSet.getString("title"));
            task.setDescription(resultSet.getString("description"));
            task.setDate_create(resultSet.getDate("date_create"));
            task.setDate_end(resultSet.getDate("date_end"));
            task.setPriority(resultSet.getInt("priority"));
            task.setStatus(resultSet.getString("status"));
            task.setAttachments(getAttachments(resultSet.getLong("users_id"), resultSet.getLong("id")));


            preparedStatement.close();
            resultSet.close();

            connection.commit();
            connection.close();
            return Optional.of(task);
        }

        preparedStatement.close();
        resultSet.close();

        connection.commit();
        connection.close();

        return Optional.empty();
    }
    //все задачи!! без вложений у юзера

    public List<Task> getAllTasks(Long user_id, Date date_create) throws SQLException {
        String sql = "SELECT\n" +
                "    t.id,\n" +
                "    t.users_id,\n" +
                "    t.title,\n" +
                "    t.description,\n" +
                "    t.date_create,\n" +
                "    t.date_end,\n" +
                "    t.priority,\n" +
                "    t.status\n" +
                "FROM task t\n" +
                "JOIN users u ON t.users_id = u.id\n" +
                "WHERE u.id = ?\n" +
                "AND DATE(t.date_create) = ?";

        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, user_id);
        preparedStatement.setDate(2, date_create);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Task> tasks = new ArrayList<>();

        while (resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getLong("id"));
            task.setUsers_id(resultSet.getLong("users_id"));
            task.setTitle(resultSet.getString("title"));
            task.setDescription(resultSet.getString("description"));
            task.setDate_create(resultSet.getDate("date_create"));
            task.setDate_end(resultSet.getDate("date_end"));
            task.setPriority(resultSet.getInt("priority"));
            task.setStatus(resultSet.getString("status"));
            task.setAttachments(getAttachments(resultSet.getLong("users_id"), resultSet.getLong("id")));
            System.out.println(task.getAttachments() + "getTasksForUser");
            task.setTags(getTags(resultSet.getLong("id")));
            System.out.println(task.getTags() + "getTasksForUser");
            tasks.add(task);
        }
        preparedStatement.close();
        resultSet.close();
        connection.commit();
        connection.close();


        return tasks;
    }

    public List<Attachments> getAttachments(Long user_id, Long task_id) throws SQLException {
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


    public List<Tag> getTags(Long task_id) throws SQLException {
        String sql = "select id, name from task_tag tt join tag on tag.id = tt.tag_id where tt.task_id = ?\n";
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, task_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Tag> tags = new ArrayList<>();
        while (resultSet.next()) {
            Tag tag = new Tag();
            tag.setId(Long.valueOf(resultSet.getString("id")));
            tag.setName(resultSet.getString("name"));
            tags.add(tag);
        }

        resultSet.close();
        preparedStatement.close();
        connection.commit();
        connection.close();
        return tags;

    }



    //TODO вынести в отдельный репозиторяи два последних можета
}
