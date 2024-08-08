package com.crud.tasks.controller;


import com.crud.tasks.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrudController {

    @Autowired
    private CrudService crudService;

    @GetMapping("/")
    public String showTaskCount(Model model) {
        int taskCount = crudService.getTaskCount();
        model.addAttribute("taskCount", taskCount);
        model.addAttribute("message", "Liczba dostępnych zadań w bazie danych: " + taskCount);
        model.addAttribute("admin_name", "Admin");
        model.addAttribute("button", "Zobacz zadania");
        model.addAttribute("tasks_url", "/tasks");
        model.addAttribute("goodbye_message", "Miłego dnia!");
        model.addAttribute("company_details", "Twoja firma");

        return "taskTemplate";
    }
}

