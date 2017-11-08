package com.hw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
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

    @OneToMany(mappedBy = "superCategory")
    private Set<Category> subCategory;

    @ManyToOne
    private Category superCategory;
}
