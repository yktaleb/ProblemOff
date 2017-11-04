package com.hw.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Correspondence {
    @Id
    @GeneratedValue
    private Long id;

    private User firstInterlocutor;
    private User secondInterlocutor;
}
