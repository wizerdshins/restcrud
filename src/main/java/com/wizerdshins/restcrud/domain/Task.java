package com.wizerdshins.restcrud.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Data
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


    @Override
    public String toString() {
        return id + " : " + description;
    }

}
