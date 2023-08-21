package com.example.ProjectBBVA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reservacion")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date check_In;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date check_out;

    @NotNull
    private int num_Person;

    @ManyToOne
    @JoinColumn(name = "HabitacionId")
    private Room rooms;

    @ManyToOne
    @JoinColumn(name = "ClienteId")
    private Client client;

    private boolean state = true;

}
