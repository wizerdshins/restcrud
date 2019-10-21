package com.wizerdshins.restcrud.controller;

import com.wizerdshins.restcrud.domain.Comment;
import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.CommentRepository;
import com.wizerdshins.restcrud.repository.TaskRepository;
import com.wizerdshins.restcrud.service.TaskService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private TaskRepository taskRepository;
    private TaskService service;

    private Task tempTask;

    private CommentRepository commentRepository;

    public MainController(TaskRepository taskRepository, TaskService service, CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.service = service;
        this.commentRepository = commentRepository;
    }

    /*
    TODO replace by service methods
     */

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
        return "update_status";

//        if (id.isPresent()) {
//            Task task = taskRepository.getOne(id.get());
//            model.addAttribute("task", task);
//        } else {
//            model.addAttribute("task", new Task(""));
//        }
//        return "add-edit-task";
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

    @GetMapping(path = "delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Task deleteTask = taskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Error, motherfucker!"));
        taskRepository.delete(deleteTask);
        model.addAttribute("tasks", taskRepository.findAll());
        return "redirect:/";
    }

    /*
    methods for adding comments
     */

    @GetMapping(path = "get_comments/{id}")
    public String showComments(@PathVariable("id") long id, Model model) {
        Task task = taskRepository.getOne(id);
        tempTask = task;
        List<Comment> listComments = task.getComments();
        System.out.println(task);
        model.addAttribute("comments", listComments);
        return "show_comments";
    }

    @GetMapping(path = "comment_form")
    public String getCommentForm(Comment comment) {
        return "add_comments";
    }

    @Transactional
    @RequestMapping(path = "/comment",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST)
    public String comment(Comment comment) {
        comment.setTask(tempTask);
        commentRepository.save(comment);
        System.out.println(comment);
        return "redirect:/";
    }

}
