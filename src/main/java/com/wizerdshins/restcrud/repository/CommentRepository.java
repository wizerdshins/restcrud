package com.wizerdshins.restcrud.repository;

import com.wizerdshins.restcrud.domain.Comment;
import com.wizerdshins.restcrud.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTask(Task task);

}
