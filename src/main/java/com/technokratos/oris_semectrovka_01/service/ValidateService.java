package com.technokratos.oris_semectrovka_01.service;

import com.technokratos.oris_semectrovka_01.entity.Task;
import com.technokratos.oris_semectrovka_01.entity.User;

public class ValidateService {
    public static String getValidDate(Task task) {
        System.out.println("YASLLLSLSL" + task);
        if(task.getDate_end().before(task.getDate_create())) {
            return "Дата окончания раньше даты создания!";
        }
        return null;
    }

    public static String getValidPassword(User user) {
        if (user.getLogin().length() < 7) {

            return "Пароль слишком короткий!";
        }
        return null;

    }
}
