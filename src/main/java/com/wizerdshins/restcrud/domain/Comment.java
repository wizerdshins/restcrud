package com.wizerdshins.restcrud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
