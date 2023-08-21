package com.example.ProjectBBVA.Services;

import com.example.ProjectBBVA.Handler.HotelNotFoundException;
import com.example.ProjectBBVA.Model.Hotel;
import com.example.ProjectBBVA.Persistences.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired // inyecta hacia el repositorio
    HotelRepository hotelRepository;

    public List<Hotel> HotelFindAll(){
        return hotelRepository.findAll();
    }
    public Hotel HotelFindById(Long id){
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }
    public Hotel HotelSave(Hotel new_hotel){
        return hotelRepository.save(new_hotel);
    }
    public String HotelDelete(Long id) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setState(false);
                    hotelRepository.save(hotel);
                    return "Hotel eliminado";
                })
                .orElseGet(() -> {
                    return "no se pudo eliminar el Hotel";
                });
    }
    public Hotel HotelUpdate(Hotel newHotel, Long id) {

        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setName(newHotel.getName());
                    hotel.setDescription(newHotel.getDescription());
                    hotel.setState(newHotel.isState());
                    hotel.setCategory(newHotel.getCategory());
                    hotel.setOrigin(newHotel.getOrigin());
                    hotel.setPhone(newHotel.getPhone());
                    return hotelRepository.save(hotel);
                })
                .orElseGet(() -> {
                    newHotel.setId(id);
                    return hotelRepository.save(newHotel);
                });
    }
}
