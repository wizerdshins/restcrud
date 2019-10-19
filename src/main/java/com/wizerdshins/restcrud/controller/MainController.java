package com.wizerdshins.restcrud.controller;

import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.TaskRepository;
import com.wizerdshins.restcrud.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {

    private TaskRepository taskRepository;
    private TaskService service;

    public MainController(TaskRepository taskRepository, TaskService service) {
        this.taskRepository = taskRepository;
        this.service = service;
    }

    /*
    TODO replace by service methods
     */

//    @GetMapping
//    public Page<Task> showAll(@PageableDefault Pageable pageable) {
//        return taskRepository.findAll(pageable);
//    }

//    @GetMapping
//    public List<Task> showAll() {
//        return taskRepository.findAll();
//    }

    @GetMapping
    public String showAll(Model model) {

        List<Task> list = taskRepository.findAll();
        model.addAttribute("tasks", list);
        return "index";
    }

    @GetMapping(path = {"/edit", "/edit/{id}"}) // TODO fix this shit
    public String edit(@PathVariable("id") long id, Model model) {

        Task updatedTask = taskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Error, motherfucker!"));
        model.addAttribute("task", updatedTask);
//        System.out.println(updatedTask.getDescription());
//        System.out.println(updatedTask.getStatus());
        return "update_status";

//        if (id.isPresent()) {
//            Task task = taskRepository.getOne(id.get());
//            model.addAttribute("task", task);
//        } else {
//            model.addAttribute("task", new Task(""));
//        }
//        return "add-edit-task";
    }

    /*
    TODO put in service methods
     */
    @RequestMapping(path = "/update/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST)
    public String update(long id, Task task, String status) {
        Task updatedTask = taskRepository.getOne(id);
        updatedTask.setStatus(status);
        System.out.println(updatedTask.getDescription());
        System.out.println(updatedTask.getStatus());
        taskRepository.save(updatedTask);
        return "redirect:/";
    }

    @GetMapping("new")
    public String addNewTask(Task task) {
        return "add_task";
    }

    @RequestMapping(path = "/create",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST)
    public String create(Task task) {
        service.create(task);
        return "redirect:/";
    }

//    @DeleteMapping("delete/{id}")
//    public void delete(@PathVariable("id") Task deleteTask) {
//        taskRepository.delete(deleteTask);
//    }

    @GetMapping(path = "delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Task deleteTask = taskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Error, motherfucker!"));
        taskRepository.delete(deleteTask);
        model.addAttribute("tasks", taskRepository.findAll());
        return "redirect:/";
    }

    /*
    TODO add methods for updating status and comments
     */

}
