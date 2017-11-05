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
public class TypeClass {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "typeClass")
    private Set<Type> types;

    @OneToMany(mappedBy = "superClass")
    private Set<TypeClass> subClasses;

    @ManyToOne
    private TypeClass superClass;
}
