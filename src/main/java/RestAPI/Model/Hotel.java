package RestAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String Nombre;

    @NotNull
    @Column(length = 100)
    private String Lugar;

    @NotNull
    private int Categoria;

    @NotNull
    private int Telefono;

    @Column(length = 150)
    private String Descripccion;

    private boolean estado = true;


}
