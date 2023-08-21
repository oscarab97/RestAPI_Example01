package com.example.ProjectBBVA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Habitacion")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private int members;

    @ManyToOne
    @JoinColumn(name="HotelId")
    private Room hotel;

    @NotNull
    private String type;

    @NotNull
    private double cost;

    @NotNull
    @Column(length = 150)
    private String description;

    private boolean state = true;
}
