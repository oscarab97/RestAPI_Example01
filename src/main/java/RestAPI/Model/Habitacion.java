package RestAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private int Numero;

    @ManyToOne
    @JoinColumn(name="HotelId")
    private Hotel hotel;

    @NotNull
    private String Tipo;

    @NotNull
    private double Costo;

    @NotNull
    @Column(length = 150)
    private String Descripccion;

    private boolean estado = true;

}
