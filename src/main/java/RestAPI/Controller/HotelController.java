package RestAPI.Controller;

import RestAPI.Handler.HotelNotFoundException;
import RestAPI.Model.Hotel;
import RestAPI.Persistence.HotelRepository;
import RestAPI.Services.HotelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HotelController {
    @Autowired // inyecta el objeto Repositorio
    HotelRepository hotelRepository;
    @Autowired //Inyecta el objeto servicio
    HotelService hotelService;

    @ApiOperation(value = "Muestra todos los datos disponibles",notes = "En este metodo se genera una llamada a la base de datos solicitando todos los datos disponibles en la tabla Hotel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Los recursos se obtuvieron correctamente", response = Hotel.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @GetMapping("/hotel") // Metodo para regresar todos los datos que tiene la tabla Hotel
    List<Hotel> all() {
        return hotelRepository.findAll();
    }

    @ApiOperation(value = "Muestra un dato especifico con el Id",notes = "En este metodo se genera una llamada a la base de datos solicitando los datos de la columna especifica mediante el Id en la tabla Hotel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Hotel.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @GetMapping("/hotel/{id}") // Metodo para obtener un dato especifico mediante un Id (identificador)
    Hotel getById(@PathVariable Long id) {

        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @ApiOperation(value = "Valida y Guarda una estructura de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se validan y se guardan los datos nuevos en la tabla Hotel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se agrego correctamente", response = Hotel.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @PostMapping("/hotel") // Metodo que validar y guardar la estructura de datos introducida (Json o xml) en la DB
    Hotel createNew(@Valid @RequestBody Hotel newHotel) {

        return hotelRepository.save(newHotel);
    }
    @ApiOperation(value = "Elimina de forma logica una columna especifica",notes = "En este metodo se genera una llamada a la base de datos en la cual se elimina de forma logica, cambiando la propiedad de estado de una columna en \"falso\" en la tabla Hotel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se elimino correctamente", response = Hotel.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @DeleteMapping("/hotel/{id}")//Metodo para eliminar un dato especifico en la tabla Item mediante el Id (identificador)
    String delete(@PathVariable Long id) {
        return hotelService.deleteHotel(id);
    }
    @ApiOperation(value = "Modifca los valores en la base de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se modifica una estructura de datos localizado mediante el Id en la tabla Hotel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se modifico correctamente", response = Hotel.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @PutMapping("/hotel/{id}")//Metodo para Editar los valores que contengan el mismo Id (identificador) dentro de la DB
    Hotel updateOrCreate(@Valid @RequestBody Hotel newHotel, @PathVariable Long id) {

        return hotelService.updateHotel(newHotel,id);
    }
}
