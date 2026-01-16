package com.technokratos.oris_semectrovka_01.service;

import com.technokratos.oris_semectrovka_01.DAO.UserDAOImpl;
import com.technokratos.oris_semectrovka_01.entity.Attachments;
import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Optional<User> registrationUser(User user) {


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            if (userDAO.findBuLogin(user).isEmpty()) {
                userDAO.save(user);
                return Optional.of(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public boolean loginUser(User user)  {
        try {

            System.out.println(user);
            Optional<User> userDB = userDAO.findBuLogin(user);
            System.out.println(userDB);


            if(userDB.isPresent()) {

                String passwordIn = user.getPassword();
                String hashedPassword = userDB.get().getPassword();
                user.setId(userDB.get().getId());

                return passwordEncoder.matches(passwordIn, hashedPassword);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public String changeData(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userDAO.update(user);
            return null;
        } catch (SQLException e) {
            return "Не удалось обновить данные!";
        }
    }


    public String delete(Long user_id) {
        try {
            userDAO.delete(user_id);
            return null;
        } catch (SQLException e) {
           return "Не удалось удалить задачу!";
        }
    }

    public Optional<User> getUser(Long user_id) {
        try {
            return userDAO.findById(user_id);
        } catch (SQLException e) {
            return null;
        }
    }

}
