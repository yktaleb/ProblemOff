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
public class Service {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @ManyToOne
    private User user;

    @ManyToMany
    private Set<Region> regions;

    @ManyToOne
    private Type type;
}
