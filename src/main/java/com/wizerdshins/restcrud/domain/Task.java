package com.wizerdshins.restcrud.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private String status;

    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments;

    public Task(String description) {
        this.description = description;
        this.status = "New"; // TODO remove by enum or something like that
    }

    public String getComments() {

        StringBuilder builder = new StringBuilder();
        if (comments.isEmpty()) return "No comments yet";
        int index = 1;
        for (Comment comment : comments) {
            builder.append(index + ". " + comment + " ");
            index++;
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return id + " : " + description;
    }

}
