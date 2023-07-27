package RestAPI.Services;

import RestAPI.Model.Hotel;
import RestAPI.Persistence.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired // inyecta hacia el repositorio
    HotelRepository hotelRepository;

    public String deleteHotel(Long id){
        return hotelRepository.findById(id)
                .map(hotel->{
                    hotel.setEstado(false);
                    hotelRepository.save(hotel);
                    return "Hotel eliminado";
                })
                .orElseGet(()->{
                    return  "no se pudo eliminar el hotel";
                });
    }
    public Hotel updateHotel(Hotel newHotel, Long id) {

        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setNombre(newHotel.getNombre());
                    hotel.setDescripccion(newHotel.getDescripccion());
                    hotel.setEstado(newHotel.isEstado());
                    hotel.setCategoria(newHotel.getCategoria());
                    hotel.setLugar(newHotel.getLugar());
                    hotel.setTelefono(newHotel.getTelefono());
                    return hotelRepository.save(hotel);
                })
                .orElseGet(() -> {
                    newHotel.setId(id);
                    return hotelRepository.save(newHotel);
                });
    }
}
