package com.hw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Type> types;

    @OneToMany(mappedBy = "superCategory",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Category> subCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category superCategory;
}
