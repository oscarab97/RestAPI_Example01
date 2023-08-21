package com.example.ProjectBBVA.Controllers;

import com.example.ProjectBBVA.Model.Room;
import com.example.ProjectBBVA.Services.RoomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoomController {
    @Autowired //Inyecta el objeto servicio
    RoomService roomService;

    @ApiOperation(value = "Muestra todos los datos disponibles",notes = "En este metodo se genera una llamada a la base de datos solicitando todos los datos disponibles en la tabla Room")
    @GetMapping("/room") // Metodo para regresar todos los datos que tiene la tabla Room
    List<Room> all() {
        return roomService.RoomFindAll();
    }

    @ApiOperation(value = "Muestra un dato especifico con el Id",notes = "En este metodo se genera una llamada a la base de datos solicitando los datos de la columna especifica mediante el Id en la tabla Room")
    @GetMapping("/room/{id}") // Metodo para obtener un dato especifico mediante un Id (identificador)
    Room getById(@PathVariable Long id) {

        return roomService.RoomFindById(id);
    }

    @ApiOperation(value = "Valida y Guarda una estructura de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se validan y se guardan los datos nuevos en la tabla Room")
    @PostMapping("/room") // Metodo que validar y guardar la estructura de datos introducida (Json o xml) en la DB
    Room createNew(@Valid @RequestBody Room newRoom) {

        return roomService.RoomSave(newRoom);
    }

    @ApiOperation(value = "Elimina de forma logica una columna especifica",notes = "En este metodo se genera una llamada a la base de datos en la cual se elimina de forma logica, cambiando la propiedad de estado de una columna en \"falso\" en la tabla Room")
    @DeleteMapping("/room/{id}")//Metodo para eliminar un dato especifico en la tabla Item mediante el Id (identificador)
    String delete(@PathVariable Long id) {
        return roomService.RoomDelete(id);
    }

    @ApiOperation(value = "Modifca los valores en la base de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se modifica una estructura de datos localizado mediante el Id en la tabla Room")
    @PutMapping("/room/{id}")//Metodo para Editar los valores que contengan el mismo Id (identificador) dentro de la DB
    Room updateOrCreate(@Valid @RequestBody Room newRoom, @PathVariable Long id) {

        return roomService.RoomUpdate(newRoom,id);
    }
}
