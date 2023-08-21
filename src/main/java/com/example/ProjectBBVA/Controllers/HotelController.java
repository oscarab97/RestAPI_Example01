package com.example.ProjectBBVA.Controllers;
import com.example.ProjectBBVA.Model.Hotel;
import com.example.ProjectBBVA.Services.HotelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class HotelController {
    @Autowired //Injects the service object
    HotelService hotelService;

    @ApiOperation(value = "Show all available data",notes = "This method generates a call to the database requesting all available data in the Hotel table")
    @GetMapping("/hotel") // Method to return all data in the table Hotel
    List<Hotel> all() {
        return hotelService.HotelFindAll();
    }

    @ApiOperation(value = "Displays a specific data with the Id",notes = "This method generates a call to the database requesting the data of the specified column by means of the Id in the Hotel table.")
    @GetMapping("/hotel/{id}") // Method to obtain a specific data by means of an Id
    Hotel getById(@PathVariable Long id) {

        return hotelService.HotelFindById(id);
    }

    @ApiOperation(value = "Validate and save a data structure",notes = "This method generates a call to the database in which the new data is validated and stored in the Hotel table.")
    @PostMapping("/hotel") // Method that validates and saves the data structure (Json or xml) in the DB.
    Hotel createNew(@Valid @RequestBody Hotel newHotel) {

        return hotelService.HotelSave(newHotel);
    }
    @ApiOperation(value = "Logically deletes a specific column.",notes = "This method generates a call to the database in which it is logically deleted, changing the status property of a column to \"FALSE\" in the Hotel table.")
    @DeleteMapping("/hotel/{id}")//Method to delete a specific data in the Item table using the Item Id.
    String delete(@PathVariable Long id) {
        return hotelService.HotelDelete(id);
    }

    @ApiOperation(value = "Modify the values in the database",notes = "This method generates a call to the database in which a data structure located by the Id in the Hotel table is modified.")
    @PutMapping("/hotel/{id}")//Method to edit values containing the same Id within the DB
    Hotel updateOrCreate(@Valid @RequestBody Hotel newHotel, @PathVariable Long id) {

        return hotelService.HotelUpdate(newHotel,id);
    }

}
