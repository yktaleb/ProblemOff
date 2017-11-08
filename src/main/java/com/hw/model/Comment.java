package com.hw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private Date writingDate;
    private String value;

    @ManyToOne
    private User receiver;
    @ManyToOne
    private User author;
    @ManyToOne
    private CommentMark commentMark;
}
