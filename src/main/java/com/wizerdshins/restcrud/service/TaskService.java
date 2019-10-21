package com.wizerdshins.restcrud.service;

import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.TaskRepository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void create(Task task) {
        task.setStatus("New");
        taskRepository.save(task);
    }

    public void update(long id, String status) {

        Task updatedTask = taskRepository.getOne(id);
        updatedTask.setStatus(status);
        taskRepository.save(updatedTask);
    }

    public void delete(long id) {
        Task deletedTask = taskRepository.getOne(id);
        taskRepository.delete(deletedTask);
    }


}
