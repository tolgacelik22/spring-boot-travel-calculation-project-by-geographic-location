package com.example.myspringproject.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "store_enterances", indexes = @Index(columnList = "courierId"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class StoreEnterances implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_store_enterances", allocationSize = 1)
    @GeneratedValue(generator = "seq_store_enterances", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 500)
    private String storeName;
    private int storeId;
    private String time;
    private int courierId;

}
