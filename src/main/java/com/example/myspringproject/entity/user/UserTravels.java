package com.example.myspringproject.entity.user;

import lombok.*;
import org.nustaq.serialization.annotations.Serialize;

import javax.persistence.*;

@Entity
@Table(name = "user_travels_table", indexes = @Index(columnList = "courierId"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class UserTravels {
    @Id
    @SequenceGenerator(name = "seq_user_travels", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_travels", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100)
    private double lat;

    @Column(length = 100)
    private double lng;

    private String time;

    private int courierId;
}
