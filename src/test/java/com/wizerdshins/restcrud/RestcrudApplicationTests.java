package com.wizerdshins.restcrud;

import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RestcrudApplicationTests {


    /*
    more tests... in next version...
     */

    @MockBean
    private TaskRepository mockRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void create() {
        Task task = new Task("Description");
        mockRepository.save(task);
        assertNotNull(task);
    }

    @Test
    void update() {
        Task task = new Task("Task");
        mockRepository.save(task);
        Task updatedTask = new Task("Another task");
        mockRepository.save(updatedTask);
        updatedTask.setStatus("Open");
        assertNotNull(updatedTask);
        assertNotEquals(task.getStatus(), updatedTask.getStatus());
    }

    @Test
    void delete() {
        Task task = new Task(1L, "Old plain task",
                "New", Collections.emptyList());
        mockRepository.save(task);
        mockRepository.deleteById(1L);
    }


}
