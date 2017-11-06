package com.hw.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskMark {
    @Id
    @GeneratedValue
    private Long id;
    private String value;

    @OneToMany(mappedBy = "taskMark")
    private Set<TaskInstance> taskInstances;
}