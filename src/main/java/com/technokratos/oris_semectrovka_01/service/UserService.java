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


    public User registrationUser(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userDAO.save(user);
            request.getSession().setAttribute("id", user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean loginUser(HttpServletRequest request)  {
        try {
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            Optional<User> userDB = userDAO.find(user);
            System.out.println(userDB);


            if(userDB.isPresent()) {

                String passwordIn = user.getPassword();
                String hashedPassword = userDB.get().getPassword();
                request.getSession().setAttribute("id", userDB.get().getId());

                return passwordEncoder.matches(passwordIn, hashedPassword);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void changeData(HttpServletRequest request, String login) {
        User user = new User();
        System.out.println("login" + login);
        user.setEmail(request.getParameter("email"));
        user.setPassword(passwordEncoder.encode(request.getParameter("password")));
        user.setName(request.getParameter("name"));
        System.out.println(user.getName());
        user.setLogin(login);
        try {
            userDAO.update(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllask(HttpSession session) {
        User user = new User();
        user.setLogin((String) session.getAttribute("login"));

        try {

            return userDAO.findAllTaskForUser(user);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    public void delete(HttpServletRequest request) {
        Long user_id = (Long) request.getSession().getAttribute("id");
        try {
            userDAO.delete(user_id);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<User> getUser(HttpServletRequest request) {
        Long user_id = (Long) request.getSession().getAttribute("id");
        try {
            return userDAO.findById(user_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
