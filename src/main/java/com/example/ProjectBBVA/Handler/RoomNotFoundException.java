package com.example.ProjectBBVA.Handler;

public class RoomNotFoundException extends  RuntimeException{
    private Long id;
    public RoomNotFoundException(Long id){
        super("Could not find item" + id);
    }

}
