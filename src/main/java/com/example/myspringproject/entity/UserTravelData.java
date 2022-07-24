package com.example.myspringproject.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_travel_data", indexes = @Index(columnList = "courierId"), uniqueConstraints={@UniqueConstraint(columnNames={"courierId"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class UserTravelData implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_user_travel_data", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_travel_data", strategy = GenerationType.SEQUENCE)
    private Long id;
    private int courierId;
    private long totalTravel;
}
