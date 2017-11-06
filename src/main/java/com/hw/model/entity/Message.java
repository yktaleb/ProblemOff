package com.hw.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateOfCome;
    private String context;
    private Boolean isRead;

    @ManyToOne
    private Correspondence correspondence;

    @ManyToOne
    private MessageStatus messageStatus;
}
