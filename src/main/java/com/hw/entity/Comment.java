package com.hw.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
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
    @OneToOne
    private User author;
    @ManyToOne
    private CommentMark commentMark;
}
