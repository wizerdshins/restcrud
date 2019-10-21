package com.wizerdshins.restcrud.domain;

import lombok.*;

import javax.persistence.*;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Comment(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
