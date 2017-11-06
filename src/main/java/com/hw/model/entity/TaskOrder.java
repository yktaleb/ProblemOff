package com.hw.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskOrder {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

    @ManyToOne
    private Proposal proposal;
}
