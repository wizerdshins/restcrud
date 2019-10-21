package com.wizerdshins.restcrud;

import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestcrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(TaskRepository taskRepository) {

        return (args -> {
            taskRepository.save(new Task("This is task description"));
            taskRepository.save(new Task("Another task description"));
        });
    }

}
