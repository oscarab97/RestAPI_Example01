package com.example.ProjectBBVA.Handler;

public class HotelNotFoundException extends  RuntimeException{
    private Long id;
    public HotelNotFoundException(Long id){
        super("Could not find item" + id);
    }

}
