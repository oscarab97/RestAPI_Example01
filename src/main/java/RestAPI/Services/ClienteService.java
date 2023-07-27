package RestAPI.Services;

import RestAPI.Model.Cliente;
import RestAPI.Persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired // inyecta hacia el repositorio
    ClienteRepository clienteRepository;

    public String deleteCliente(Long id){
        return clienteRepository.findById(id)
                .map(cliente->{
                    cliente.setEstado(false);
                    clienteRepository.save(cliente);
                    return "Cliente eliminado";
                })
                .orElseGet(()->{
                     return  "no se pudo eliminar el Cliente";
                });
    }
    public Cliente clienteUpdate(Cliente newCliente, Long id) {

        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(newCliente.getNombre());
                    cliente.setEmail(newCliente.getEmail());
                    cliente.setTelefono(newCliente.getTelefono());
                    cliente.setPais(newCliente.getPais());
                    return clienteRepository.save(cliente);
                })
                .orElseGet(() -> {
                    newCliente.setId(id);
                    return clienteRepository.save(newCliente);
                });
    }
}
