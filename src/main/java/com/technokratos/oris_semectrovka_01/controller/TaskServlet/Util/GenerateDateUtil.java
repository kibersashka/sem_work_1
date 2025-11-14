package com.technokratos.oris_semectrovka_01.controller.TaskServlet.Util;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GenerateDateUtil {
    public static Date generateData(HttpServletRequest request) {
        String yearPar = request.getParameter("year");
        String monthPar = request.getParameter("month");
        String dayPar = request.getParameter("day");

        // Получаем текущую дату
        int year = (yearPar == null) ? LocalDate.now().getYear() : Integer.parseInt(yearPar);
        int month = (monthPar == null) ? LocalDate.now().getMonthValue() : Integer.parseInt(monthPar);
        int day = (dayPar == null) ? LocalDate.now().getDayOfMonth() : Integer.parseInt(dayPar);

        // Корректируем месяц и год если нужно
        if (month < 1) { month = 12; year--; }
        if (month > 12) { month = 1; year++; }

        // Корректируем день если он превышает количество дней в месяце
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        if (day > daysInMonth) {
            day = daysInMonth;
        }
        if (day < 1) {
            day = 1;
        }



        LocalDate localDate = yearMonth.atDay(1);
        int firstDayOfWeek = localDate.getDayOfWeek().getValue();
        System.out.println("firstDayOfWeek: " + firstDayOfWeek);



        List<List<Integer>> weeks = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        // Пустые ячейки для не дней месяца
        for (int i = 1; i < firstDayOfWeek; i++) {
            current.add(null);
        }

        // Заполнить числа месяца
        for (int i = 1; i <= daysInMonth; i++) {
            current.add(i);
            if(current.size() == 7) {
                weeks.add(current);
                current = new ArrayList<>();
            }
        }
        // Если какие-то дни не полные
        if(!current.isEmpty()) {
            while (current.size() < 7) {
                current.add(null);
            }
            weeks.add(current);
        }

        request.setAttribute("weeks", weeks);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("dayPick", day); // Добавляем выбранный день

        request.setAttribute("monthName",
                yearMonth.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));

        request.setAttribute("prevMonth", month == 1 ? 12 : month - 1);
        request.setAttribute("nextMonth", month == 12 ? 1 : month + 1);
        request.setAttribute("prevYear", month == 1 ? year - 1 : year);
        request.setAttribute("nextYear", month == 12 ? year + 1 : year);

        // Возвращаем Date с проверенным днем
        return new Date(year - 1900, month - 1, day);
    }
    public static Date generateLocalData(HttpServletRequest request) {
        // Всегда используем сегодняшнюю дату
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate localDate = yearMonth.atDay(1);
        int firstDayOfWeek = localDate.getDayOfWeek().getValue();
        System.out.println("firstDayOfWeek: " + firstDayOfWeek);

        int daysInMonth = yearMonth.lengthOfMonth();

        List<List<Integer>> weeks = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        // Пустые ячейки для не дней месяца
        for (int i = 1; i < firstDayOfWeek; i++) {
            current.add(null);
        }

        // Заполнить числа месяца
        for (int i = 1; i <= daysInMonth; i++) {
            current.add(i);
            if(current.size() == 7) {
                weeks.add(current);
                current = new ArrayList<>();
            }
        }
        // Если какие-то дни не полные
        if(!current.isEmpty()) {
            while (current.size() < 7) {
                current.add(null);
            }
            weeks.add(current);
        }

        request.setAttribute("weeks", weeks);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.getSession().setAttribute("dayPick", day);
        request.getSession().setAttribute("monthPick", month);
        request.getSession().setAttribute("yearPick", year);

        request.setAttribute("monthName",
                yearMonth.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));

        request.setAttribute("prevMonth", month == 1 ? 12 : month - 1);
        request.setAttribute("nextMonth", month == 12 ? 1 : month + 1);
        request.setAttribute("prevYear", month == 1 ? year - 1 : year);
        request.setAttribute("nextYear", month == 12 ? year + 1 : year);

        // Возвращаем сегодняшнюю дату
        return new Date(year - 1900, month - 1, day);
    }

}