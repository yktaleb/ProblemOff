package com.hw.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private Set<Service> services;

    @ManyToOne
    private TypeClass typeClass;

    @OneToMany(mappedBy = "type")
    private Set<Task> tasks;
}
