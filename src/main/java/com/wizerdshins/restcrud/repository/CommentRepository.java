package com.wizerdshins.restcrud.repository;

import com.wizerdshins.restcrud.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("from Comment c where c.task.id = ?1")
    List<Comment> findAllById(long id);

}
