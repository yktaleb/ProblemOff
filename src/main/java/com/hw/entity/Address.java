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
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String apartmentNumber;

    @OneToMany(mappedBy = "address")
    private User user;

    @OneToMany(mappedBy = "address")
    private Set<Task> tasks;

    @ManyToOne
    private Location location;
}
