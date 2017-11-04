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
public class Service {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @ManyToOne
    private User user;

    @ManyToOne
    private Set<Region> regions;
}
