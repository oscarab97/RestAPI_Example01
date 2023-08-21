package com.example.ProjectBBVA.Services;

import com.example.ProjectBBVA.Handler.ClientNotFoundException;
import com.example.ProjectBBVA.Model.Client;
import com.example.ProjectBBVA.Persistences.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService {
    @Autowired // inyecta hacia el repositorio
    ClientRepository clientRepository;

    public List<Client> ClientFindAll(){
        return clientRepository.findAll();
    }
    public Client ClientFindById(Long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }
    public Client ClientSave(Client new_client){
        return clientRepository.save(new_client);
    }
    public String ClientDelete(Long id){
        return clientRepository.findById(id)
                .map(client->{
                    client.setState(false);
                    clientRepository.save(client);
                    return "Client eliminado";
                })
                .orElseGet(()->{
                    return  "no se pudo eliminar el Client";
                });
    }
    public Client ClientUpdate(Client newClient, Long id) {

        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(newClient.getName());
                    client.setEmail(newClient.getEmail());
                    client.setPhone(newClient.getPhone());
                    client.setCountry(newClient.getCountry());
                    return clientRepository.save(client);
                })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return clientRepository.save(newClient);
                });
    }
}
