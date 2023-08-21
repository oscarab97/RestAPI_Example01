package com.example.ProjectBBVA.Controllers;

import com.example.ProjectBBVA.Model.Booking;
import com.example.ProjectBBVA.Services.BookingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookingController {
    @Autowired //Inyecta el objeto servicio
    BookingService bookingService;

    @ApiOperation(value = "Muestra todos los datos disponibles",notes = "En este metodo se genera una llamada a la base de datos solicitando todos los datos disponibles en la tabla Booking")
    @GetMapping("/booking") // Metodo para regresar todos los datos que tiene la tabla Booking
    List<Booking> FindAll() {
        return bookingService.BookingFindAll();
    }

    @ApiOperation(value = "Muestra un dato especifico con el Id",notes = "En este metodo se genera una llamada a la base de datos solicitando los datos de la columna especifica mediante el Id en la tabla Booking")
    @GetMapping("/booking/{id}") // Metodo para obtener un dato especifico mediante un Id (identificador)
    Booking GetById(@PathVariable Long id) {

        return bookingService.BookingFindById(id);
    }

    @ApiOperation(value = "Valida y Guarda una estructura de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se validan y se guardan los datos nuevos en la tabla Booking")
    @PostMapping("/booking") // Metodo que validar y guardar la estructura de datos introducida (Json o xml) en la DB
    Booking CreateNew(@Valid @RequestBody Booking newBooking) {

        return bookingService.BookingSave(newBooking);
    }
    @ApiOperation(value = "Elimina de forma logica una columna especifica",notes = "En este metodo se genera una llamada a la base de datos en la cual se elimina de forma logica, cambiando la propiedad de estado de una columna en \"falso\" en la tabla Booking")
    @DeleteMapping("/booking/{id}")//Metodo para eliminar un dato especifico en la tabla Item mediante el Id (identificador)
    String Delete(@PathVariable Long id) {
        return bookingService.BookingDelete(id);
    }

    @ApiOperation(value = "Modifca los valores en la base de datos",notes = "En este metodo se genera una llamada a la base de datos en la cual se modifica una estructura de datos localizado mediante el Id en la tabla Booking")
    @PutMapping("/booking/{id}")//Metodo para Editar los valores que contengan el mismo Id (identificador) dentro de la DB
    Booking UpdateOrCreate(@Valid @RequestBody Booking newBooking, @PathVariable Long id) {

        return bookingService.BookingkUpdate(newBooking,id);
    }
}
