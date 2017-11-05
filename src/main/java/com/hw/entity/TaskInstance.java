package com.hw.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskInstance {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Task task;

    @ManyToOne
    private TaskMark taskMark;

}
