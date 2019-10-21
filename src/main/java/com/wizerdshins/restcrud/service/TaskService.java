package com.wizerdshins.restcrud.service;

import com.wizerdshins.restcrud.domain.Comment;
import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.CommentRepository;
import com.wizerdshins.restcrud.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void create(Task task) {
        task.setStatus("New");
        taskRepository.save(task);
    }

}
