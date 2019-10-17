package com.wizerdshins.restcrud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String description;
    @Column
    private String status;

    /*
    also list of comments will be here
     */

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


}
