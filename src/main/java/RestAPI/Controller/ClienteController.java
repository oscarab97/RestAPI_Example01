package RestAPI.Controller;

import RestAPI.Handler.ClienteNotFoundException;
import RestAPI.Model.Cliente;
import RestAPI.Persistence.ClienteRepository;
import RestAPI.Services.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;

    @ApiOperation(value = "Muestra todos los datos disponibles",notes = "En este metodo se genera una llamada a la base de datos solicitando todos los datos disponibles en la tabla Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Los recursos se obtuvieron correctamente", response = Cliente.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )

    @GetMapping("/cliente") // Metodo para regresar todos los datos que tiene la tabla cliente
    List<Cliente> all() {
        return clienteRepository.findAll();
    }

    @ApiOperation(value = "Muestra un dato especifico con el Id",notes = "En este metodo se genera una llamada a la base de datos solicitando los datos de la columna especifica mediante el Id en la tabla Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Cliente.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @GetMapping("/cliente/{id}") // Metodo para obtener un dato especifico mediante un Id (identificador)
    Cliente getById(@PathVariable Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @ApiOperation(value = "Valida y Guarda una estructura de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se validan y se guardan los datos nuevos en la tabla Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se agrego correctamente", response = Cliente.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @PostMapping("/cliente") // Metodo que validar y guardar la estructura de datos introducida (Json o xml) en la DB
    Cliente createNew(@Valid @RequestBody Cliente newCliente) {

        return clienteRepository.save(newCliente);
    }
    @ApiOperation(value = "Elimina de forma logica una columna especifica",notes = "En este metodo se genera una llamada a la base de datos en la cual se elimina de forma logica, cambiando la propiedad de estado de una columna en \"falso\" en la tabla Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se elimino correctamente", response = Cliente.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @DeleteMapping("/cliente/{id}")//Metodo para eliminar un dato especifico en la tabla cliente mediante el Id (identificador)
    String delete(@PathVariable Long id) {
        return clienteService.deleteCliente(id);
    }
    @ApiOperation(value = "Modifca los valores en la base de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se modifica una estructura de datos localizado mediante el Id en la tabla Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se modifico correctamente", response = Cliente.class ),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
    )
    @PutMapping("/cliente/{id}")//Metodo para Editar los valores que contengan el mismo Id (identificador) dentro de la DB
    Cliente updateOrCreate(@Valid @RequestBody Cliente newCliente, @PathVariable Long id) {

        return clienteService.clienteUpdate(newCliente,id);
    }

}
