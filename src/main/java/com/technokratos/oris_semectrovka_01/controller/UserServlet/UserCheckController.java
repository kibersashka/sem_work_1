package com.technokratos.oris_semectrovka_01.controller.UserServlet;

import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.entity.User;
import com.technokratos.oris_semectrovka_01.service.TaskService;
import com.technokratos.oris_semectrovka_01.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@WebServlet("/usercheck")
public class UserCheckController extends HttpServlet {

    private UserService userService  = new UserService();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.ftl").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resource = getString(request);
        response.sendRedirect(request.getContextPath() + resource);


    }

    private String getString(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        String resource = "/login";

        if(session == null || session.getAttribute("user") == null) {

            User user = new User();

            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            session = request.getSession();




            if(userService.loginUser(user)){
                session.setAttribute("id", user.getId());
                session = request.getSession();
                session.setAttribute("login", user.getLogin());
                resource = "/index";

            } else {
                request.setAttribute("error", "Не удалось найти пользователя");
                resource = "/login";
            }
        }
        return resource;
    }
}
