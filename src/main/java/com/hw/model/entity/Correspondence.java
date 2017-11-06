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
public class Correspondence {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private User interlocutor;

    @OneToMany(mappedBy = "correspondence")
    private Set<Message> messages;
}
