package RestAPI.Services;
import RestAPI.Model.Habitacion;
import RestAPI.Persistence.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService  {
    @Autowired // inyecta hacia el repositorio
    HabitacionRepository habitacionRepository;

    public String deleteHabitacion(Long id){
        return habitacionRepository.findById(id)
                .map(habitacion->{
                    habitacion.setEstado(false);
                    habitacionRepository.save(habitacion);
                    return "Habitacion eliminada";
                })
                .orElseGet(()->{
                    return  "no se pudo eliminar el habitacion";
                });
    }
    public Habitacion updateHabitacion(Habitacion newHabitacion, Long id) {

        return habitacionRepository.findById(id)
                .map(habitacion -> {
                    habitacion.setCosto(newHabitacion.getCosto());
                    habitacion.setDescripccion(newHabitacion.getDescripccion());
                    habitacion.setEstado(newHabitacion.isEstado());
                    habitacion.setNumero(newHabitacion.getNumero());
                    habitacion.setTipo(newHabitacion.getTipo());
                    return habitacionRepository.save(habitacion);
                })
                .orElseGet(() -> {
                    newHabitacion.setId(id);
                    return habitacionRepository.save(newHabitacion);
                });
    }
}
