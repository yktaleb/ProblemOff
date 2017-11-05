package com.hw.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    private Date dateBirthday;
    private String description;

    @ManyToOne
    private Address address;

    @ManyToOne
    private UserSex sex;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany(mappedBy = "receiver")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "author")
    private Set<Comment> authorComments;

    @OneToMany(mappedBy = "user")
    private Set<Service> services;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private Set<Correspondence> correspondences;

    @OneToMany(mappedBy = "interlocutor")
    private Set<Correspondence> interlocutorCorrespondences;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;

    @OneToMany(mappedBy = "user")
    private Set<TaskInstance> taskInstances;

    @OneToMany(mappedBy = "user")
    private Set<TaskOrder> taskOrders;
}
