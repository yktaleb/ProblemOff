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
public class Region {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "regions")
    private Set<Service> services;

    @OneToMany(mappedBy = "region")
    private Set<Location> locations;
}
