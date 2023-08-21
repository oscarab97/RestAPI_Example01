package com.example.ProjectBBVA.Services;

import com.example.ProjectBBVA.Handler.RoomNotFoundException;
import com.example.ProjectBBVA.Model.Room;
import com.example.ProjectBBVA.Persistences.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired // inyecta hacia el repositorio
    RoomRepository roomRepository;

    public List<Room> RoomFindAll(){
        return roomRepository.findAll();
    }
    public Room RoomFindById(Long id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }
    public Room RoomSave(Room new_room){
        return roomRepository.save(new_room);
    }
    public String RoomDelete(Long id){
        return roomRepository.findById(id)
                .map(room->{
                    room.setState(false);
                    roomRepository.save(room);
                    return "Room eliminada";
                })
                .orElseGet(()->{
                    return  "no se pudo eliminar el Room";
                });
    }
    public Room RoomUpdate(Room newRoom, Long id) {
        return roomRepository.findById(id)
                .map(room -> {
                    room.setCost(newRoom.getCost());
                    room.setDescription(newRoom.getDescription());
                    room.setState(newRoom.isState());
                    room.setMembers(newRoom.getMembers());
                    room.setType(newRoom.getType());
                    return roomRepository.save(room);
                })
                .orElseGet(() -> {
                    newRoom.setId(id);
                    return roomRepository.save(newRoom);
                });
    }
}
