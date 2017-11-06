package com.hw.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommentMark {
    @Id
    @GeneratedValue
    private Long id;
    private String value;

    @OneToMany(mappedBy = "commentMark")
    private Set<Comment> comments;
}