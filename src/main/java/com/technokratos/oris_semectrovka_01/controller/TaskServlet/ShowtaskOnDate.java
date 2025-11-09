package com.technokratos.oris_semectrovka_01.controller.TaskServlet;

import com.technokratos.oris_semectrovka_01.service.AttachmentService;
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
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet("/showtaskOnDate")
public class ShowtaskOnDate extends HttpServlet {
    private UserService userService = new UserService();
    private TaskService taskService = new TaskService();
    private AttachmentService attachmentService = new AttachmentService();
    public void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
        String yearPar = request.getParameter("year");
        String monthPar = request.getParameter("month");

        //получe текущую дату
        int year = (yearPar == null) ? LocalDate.now(ZoneId.systemDefault()).getYear() : Integer.parseInt(yearPar);
        System.out.println("Year: " + year);
        int month = ( monthPar == null) ? LocalDate.now().getMonthValue() : Integer.parseInt(monthPar);
        if (month < 1) { month = 12; year--; }
        if (month > 12) { month = 1; year++; }

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate localDate = yearMonth.atDay(1);
        int firstDayOfWeek = localDate.getDayOfWeek().getValue();
        System.out.println("firstDayOfWeek: " + firstDayOfWeek);

        int daysInMonth = yearMonth.lengthOfMonth();

        List<List<Integer>> weeks = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        //пустые ячеки для не дней месяца
        for (int i = 1; i < firstDayOfWeek; i++) {
            current.add(null);
        }

        //заполнить числа меясца
        for (int i = 1; i <= daysInMonth; i++) {
            current.add(i);
            if(current.size() == 7) {
                weeks.add(current);
                current = new ArrayList<>();
            }
        }

        //если какие-то дни не полные
        if(!current.isEmpty()) {
            while (current.size() < 7) {
                current.add(null);
            }
            weeks.add(current);
        }

        request.setAttribute("weeks", weeks);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("dayPick", request.getParameter("day"));

        request.setAttribute("monthName",
                yearMonth.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        //показать текущие задачи

        request.setAttribute("prevMonth", month == 1 ? 12 : month - 1);
        request.setAttribute("nextMonth", month == 12 ? 1 : month + 1);
        request.setAttribute("prevYear", month == 1 ? year - 1 : year);
        request.setAttribute("nextYear", month == 12 ? year + 1 : year);
        System.out.println(new Date(year, month, Integer.parseInt(request.getParameter("day"))));

        HttpSession session = request.getSession();
        session.setAttribute("yearPick", year);
        session.setAttribute("monthPick", month);
        session.setAttribute("dayPick", request.getParameter("day"));



        request.setAttribute("tasks",  taskService.getAllTasksForUser(request, new Date(year - 1900, month - 1, Integer.parseInt(request.getParameter("day")))));


        request.getRequestDispatcher("/showtaskOnDate.ftl").forward(request, res);


    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
