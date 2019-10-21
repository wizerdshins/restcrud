package com.wizerdshins.restcrud.service;

import com.wizerdshins.restcrud.domain.Comment;
import com.wizerdshins.restcrud.domain.Task;
import com.wizerdshins.restcrud.repository.CommentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllByTask(Task task) {
        return commentRepository.findAllByTask(task);
    }

    public void addComment(Comment comment, Task task) {
        comment.setTask(task);
        commentRepository.save(comment);
    }
}
