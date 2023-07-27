package RestAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reservacion")
public class Reservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date Entrada;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date Salida;

    @NotNull
    private int numPersonas;

    @ManyToOne
    @JoinColumn(name = "HabitacionId")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "ClienteId")
    private Cliente cliente;

    private boolean estado = true;


}
