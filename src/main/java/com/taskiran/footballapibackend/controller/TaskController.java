package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.service.TaskService;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/startTasks")
    public String startTasks() {
        taskService.executeTasks();
        return "Tasks executed successfully!";
    }

}
