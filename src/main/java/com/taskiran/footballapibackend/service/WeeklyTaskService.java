package com.taskiran.footballapibackend.service;

import org.springframework.scheduling.annotation.Scheduled;

public class WeeklyTaskService {

    @Scheduled(cron = "0 30 12 * * TUE")
    public void runWeeklyTask() {
        System.out.println("Bu görev her hafta Sali günü saat 12:30'da calisiyor.");
        // Buraya her hafta verileri çekmek için ilgili fonksiyon yazılabilir.
        // taskController.startTasks();
    }

    @Scheduled(cron = "0 30 12 * * FRI")
    public void runFridayTask() {
        System.out.println("Bu görev her hafta Cuma günü saat 12:30'da calisiyor.");
        // Buraya her hafta verileri çekmek için ilgili fonksiyon yazılabilir.
        // taskController.startTasks();
    }

}
