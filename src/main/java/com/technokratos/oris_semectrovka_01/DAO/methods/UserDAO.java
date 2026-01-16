package com.technokratos.oris_semectrovka_01.DAO.methods;


import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * сохранить пользователя - регистарция
 * проверить по логину паролю - вход
 * обновить
 * удалить
 *
 */
public interface UserDAO {
    Optional<User> findBuLogin(User user) throws SQLException;
    Optional<User> findById(Long user_id) throws SQLException;
    void delete(Long user_id) throws SQLException;
    boolean update(User user) throws SQLException;
    void save(User user) throws SQLException;

}
