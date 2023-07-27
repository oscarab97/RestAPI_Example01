package RestAPI.Controller;

import RestAPI.Handler.ReservacionNotFoundException;
import RestAPI.Model.Reservacion;
import RestAPI.Persistence.ReservacionRepository;
import RestAPI.Services.ReservacionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReservacionController {
    @Autowired // inyecta el objeto Repositorio
    ReservacionRepository reservacionRepository;
    @Autowired //Inyecta el objeto servicio
    ReservacionService reservacionService;

    @ApiOperation(value = "Muestra todos los datos disponibles",notes = "En este metodo se genera una llamada a la base de datos solicitando todos los datos disponibles en la tabla Reservacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Los recursos se obtuvieron correctamente", response = Reservacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @GetMapping("/reservacion") // Metodo para regresar todos los datos que tiene la tabla Reservacion
    List<Reservacion> all() {
        return reservacionRepository.findAll();
    }

    @ApiOperation(value = "Muestra un dato especifico con el Id",notes = "En este metodo se genera una llamada a la base de datos solicitando los datos de la columna especifica mediante el Id en la tabla Reservacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Reservacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @GetMapping("/reservacion/{id}") // Metodo para obtener un dato especifico mediante un Id (identificador)
    Reservacion getById(@PathVariable Long id) {

        return reservacionRepository.findById(id)
                .orElseThrow(() -> new ReservacionNotFoundException(id));
    }

    @ApiOperation(value = "Valida y Guarda una estructura de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se validan y se guardan los datos nuevos en la tabla Reservacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se agrego correctamente", response = Reservacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @PostMapping("/reservacion") // Metodo que validar y guardar la estructura de datos introducida (Json o xml) en la DB
    Reservacion createNew(@Valid @RequestBody Reservacion newReservacion) {

        return reservacionRepository.save(newReservacion);
    }
    @ApiOperation(value = "Elimina de forma logica una columna especifica",notes = "En este metodo se genera una llamada a la base de datos en la cual se elimina de forma logica, cambiando la propiedad de estado de una columna en \"falso\" en la tabla Reservacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se elimino correctamente", response = Reservacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @DeleteMapping("/reservacion/{id}")//Metodo para eliminar un dato especifico en la tabla Item mediante el Id (identificador)
    String delete(@PathVariable Long id) {
        return reservacionService.deleteReservacion(id);
    }
    @ApiOperation(value = "Modifca los valores en la base de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se modifica una estructura de datos localizado mediante el Id en la tabla Reservacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se modifico correctamente", response = Reservacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @PutMapping("/reservacion/{id}")//Metodo para Editar los valores que contengan el mismo Id (identificador) dentro de la DB
    Reservacion updateOrCreate(@Valid @RequestBody Reservacion newReservacion, @PathVariable Long id) {

        return reservacionService.updateReservacion(newReservacion,id);
    }
}
