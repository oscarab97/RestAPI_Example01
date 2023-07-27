package RestAPI.Controller;

import RestAPI.Handler.HabitacionNotFoundException;
import RestAPI.Model.Habitacion;
import RestAPI.Persistence.HabitacionRepository;
import RestAPI.Services.HabitacionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HabitacionController {
    @Autowired // inyecta el objeto Repositorio
    HabitacionRepository habitacionRepository;
    @Autowired //Inyecta el objeto servicio
    HabitacionService habitacionService;

    @ApiOperation(value = "Muestra todos los datos disponibles",notes = "En este metodo se genera una llamada a la base de datos solicitando todos los datos disponibles en la tabla Habitacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Los recursos se obtuvieron correctamente", response = Habitacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @GetMapping("/habitacion") // Metodo para regresar todos los datos que tiene la tabla Habitacion
    List<Habitacion> all() {
        return habitacionRepository.findAll();
    }

    @ApiOperation(value = "Muestra un dato especifico con el Id",notes = "En este metodo se genera una llamada a la base de datos solicitando los datos de la columna especifica mediante el Id en la tabla Habitacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Habitacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @GetMapping("/habitacion/{id}") // Metodo para obtener un dato especifico mediante un Id (identificador)
    Habitacion getById(@PathVariable Long id) {

        return habitacionRepository.findById(id)
                .orElseThrow(() -> new HabitacionNotFoundException(id));
    }

    @ApiOperation(value = "Valida y Guarda una estructura de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se validan y se guardan los datos nuevos en la tabla Habitacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se agrego correctamente", response = Habitacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @PostMapping("/habitacion") // Metodo que validar y guardar la estructura de datos introducida (Json o xml) en la DB
    Habitacion createNew(@Valid @RequestBody Habitacion newHabitacion) {

        return habitacionRepository.save(newHabitacion);
    }
    @ApiOperation(value = "Elimina de forma logica una columna especifica",notes = "En este metodo se genera una llamada a la base de datos en la cual se elimina de forma logica, cambiando la propiedad de estado de una columna en \"falso\" en la tabla Habitacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se elimino correctamente", response = Habitacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @DeleteMapping("/habitacion/{id}")//Metodo para eliminar un dato especifico en la tabla Item mediante el Id (identificador)
    String delete(@PathVariable Long id) {
        return habitacionService.deleteHabitacion(id);
    }
    @ApiOperation(value = "Modifca los valores en la base de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se modifica una estructura de datos localizado mediante el Id en la tabla Habitacion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se modifico correctamente", response = Habitacion.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @PutMapping("/habitacion/{id}")//Metodo para Editar los valores que contengan el mismo Id (identificador) dentro de la DB
    Habitacion updateOrCreate(@Valid @RequestBody Habitacion newHabitacion, @PathVariable Long id) {

        return habitacionService.updateHabitacion(newHabitacion,id);
    }
}
