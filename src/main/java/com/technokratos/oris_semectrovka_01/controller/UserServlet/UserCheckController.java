package com.technokratos.oris_semectrovka_01.controller.UserServlet;

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

@WebServlet("/usercheck")
public class UserCheckController extends HttpServlet {

    private UserService userService  = new UserService();
    private TaskService taskService = new TaskService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resource = getString(request);
        request.getRequestDispatcher(resource).forward(request, response);


    }

    private String getString(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        String resource = "/login.ftl";

        if(session == null || session.getAttribute("user") == null) {

            User user = new User();

            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));



            if(userService.loginUser(request)){
                int year = LocalDate.now(ZoneId.systemDefault()).getYear();
                System.out.println("тут");

                int month =  LocalDate.now().getMonthValue() ;
                int day = LocalDate.now().getDayOfMonth();
                if (month < 1) { month = 12; year--; }
                if (month > 12) { month = 1; year++; }

                request.setAttribute("tasks", taskService.getAllTasksForUser(request, new Date(year - 1900, month - 1, day)));

                System.out.println(request.getAttribute("tasks"));
                session = request.getSession();
                session.setAttribute("login", user.getLogin());
                session.setAttribute("login", user.getLogin());
                resource = "/index.ftl";

            } else {
                //request.setAttribute("errorMessage", "Invalid username or password");
                resource = "/login.ftl";
            }
        }

        return resource;
    }
}
