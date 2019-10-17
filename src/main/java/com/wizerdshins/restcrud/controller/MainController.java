package com.wizerdshins.restcrud.controller;

import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {

    private TaskRepository taskRepository;

    public MainController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /*
    TODO replace by service methods
     */

    @GetMapping
    public Page<Task> showAll(@PageableDefault Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Task deleteTask) {
        taskRepository.delete(deleteTask);
    }

    /*
    TODO add methods for updating status and comments
     */

}
