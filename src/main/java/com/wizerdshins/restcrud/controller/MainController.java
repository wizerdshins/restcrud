package com.wizerdshins.restcrud.controller;

import com.wizerdshins.restcrud.domain.Comment;
import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.TaskRepository;
import com.wizerdshins.restcrud.service.CommentService;
import com.wizerdshins.restcrud.service.TaskService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    private TaskRepository taskRepository;

    private TaskService service;
    private CommentService commentService;

    private Task tempTask;

    public MainController(TaskRepository taskRepository,
                          TaskService service,
                          CommentService commentService) {
        this.taskRepository = taskRepository;
        this.service = service;
        this.commentService = commentService;
    }

    @GetMapping
    public String showAll(Model model) {
        List<Task> list = taskRepository.findAll();
        model.addAttribute("tasks", list);
        return "index";
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

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String edit(@PathVariable("id") long id, Model model) {
        Task updatedTask = taskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Error, motherfucker!"));
        model.addAttribute("task", updatedTask);
        return "update_status";
    }

    @RequestMapping(path = "/update/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST)
    public String update(long id, String status) {
        service.update(id, status);
        return "redirect:/";
    }

    @GetMapping(path = "delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        service.delete(id);
        model.addAttribute("tasks", taskRepository.findAll());
        return "redirect:/";
    }

    /*
    methods for adding comments
     */

    @GetMapping(path = "get_comments/{id}")
    public String showComments(@PathVariable("id") long id, Model model) {
        tempTask = taskRepository.getOne(id);
        List<Comment> listComments = commentService.findAllByTask(tempTask);
        model.addAttribute("comments", listComments);
        return "show_comments";
    }

    @GetMapping(path = "comment_form")
    public String getCommentForm(Comment comment) {
        return "add_comments";
    }

    @RequestMapping(path = "/comment",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST)
    public String comment(Comment comment) {
        commentService.addComment(comment, tempTask);
        return "redirect:/";
    }

}
