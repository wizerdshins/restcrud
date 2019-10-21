package com.wizerdshins.restcrud.repository;

import com.wizerdshins.restcrud.domain.Comment;
import com.wizerdshins.restcrud.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Comment> findAllByCommentsIsNotNull();
}
