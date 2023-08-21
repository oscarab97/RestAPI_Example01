package com.example.ProjectBBVA.Controllers;

import com.example.ProjectBBVA.Model.Client;
import com.example.ProjectBBVA.Services.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

public class ClientController {
    @Autowired
    ClientService clientService;

    @ApiOperation(value = "Muestra todos los datos disponibles",notes = "En este metodo se genera una llamada a la base de datos solicitando todos los datos disponibles en la tabla Clients")
    @GetMapping("/client") // Metodo para regresar todos los datos que tiene la tabla client
    List<Client> all() {
        return clientService.ClientFindAll();
    }

    @ApiOperation(value = "Muestra un dato especifico con el Id",notes = "En este metodo se genera una llamada a la base de datos solicitando los datos de la columna especifica mediante el Id en la tabla Clients")
    @GetMapping("/client/{id}") // Metodo para obtener un dato especifico mediante un Id (identificador)
    Client getById(@PathVariable Long id) {

        return clientService.ClientFindById(id);
    }

    @ApiOperation(value = "Valida y Guarda una estructura de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se validan y se guardan los datos nuevos en la tabla Clients")
    @PostMapping("/client") // Metodo que validar y guardar la estructura de datos introducida (Json o xml) en la DB
    Client createNew(@Valid @RequestBody Client new_client) {

        return clientService.ClientSave(new_client);
    }
    @ApiOperation(value = "Elimina de forma logica una columna especifica",notes = "En este metodo se genera una llamada a la base de datos en la cual se elimina de forma logica, cambiando la propiedad de estado de una columna en \"falso\" en la tabla Clients")
    @DeleteMapping("/client/{id}")//Metodo para eliminar un dato especifico en la tabla client mediante el Id (identificador)
    String delete(@PathVariable Long id) {
        return clientService.ClientDelete(id);
    }
    @ApiOperation(value = "Modifca los valores en la base de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se modifica una estructura de datos localizado mediante el Id en la tabla Clients")
    @PutMapping("/client/{id}")//Metodo para Editar los valores que contengan el mismo Id (identificador) dentro de la DB
    Client updateOrCreate(@Valid @RequestBody Client newClient, @PathVariable Long id) {

        return clientService.ClientUpdate(newClient,id);
    }

    // Se usa para agrega comentarios en las respuestas
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK. El recurso se modifico correctamente", response = Client.class ),
//            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
//            @ApiResponse(code = 500, message = "Error inesperado del sistema") }
//    )

}
