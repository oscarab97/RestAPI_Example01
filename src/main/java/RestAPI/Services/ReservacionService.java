package RestAPI.Services;

import RestAPI.Model.Reservacion;
import RestAPI.Persistence.ReservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservacionService {
    @Autowired // inyecta hacia el repositorio
    ReservacionRepository reservacionRepository;

    public String deleteReservacion(Long id){
        return reservacionRepository.findById(id)
                .map(reservacion->{
                    reservacion.setEstado(false);
                    reservacionRepository.save(reservacion);
                    return "Reservacion eliminada";
                })
                .orElseGet(()->{
                    return  "no se pudo eliminar el reservacion";
                });
    }
    public Reservacion updateReservacion(Reservacion newReservacion, Long id) {

        return reservacionRepository.findById(id)
                .map(reservacion -> {
                    reservacion.setEntrada(newReservacion.getEntrada());
                    reservacion.setSalida(newReservacion.getSalida());
                    reservacion.setEstado(newReservacion.isEstado());
                    reservacion.setCliente(newReservacion.getCliente());
                    reservacion.setHabitacion(newReservacion.getHabitacion());
                    reservacion.setNumPersonas(newReservacion.getNumPersonas());
                    return reservacionRepository.save(reservacion);
                })
                .orElseGet(() -> {
                    newReservacion.setId(id);
                    return reservacionRepository.save(newReservacion);
                });
    }
}
