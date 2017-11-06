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
public class Location {
    @Id
    @GeneratedValue
    private Long id;
    private String googlePlaceId;

    @OneToMany(mappedBy = "location")
    private Set<Address> addresses;

    @ManyToOne
    private Region region;
}
