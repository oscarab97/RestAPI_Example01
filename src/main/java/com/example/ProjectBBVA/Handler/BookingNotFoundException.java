package com.example.ProjectBBVA.Handler;

import com.example.ProjectBBVA.Model.Booking;

public class BookingNotFoundException extends  RuntimeException{
    private Long id;
    public BookingNotFoundException(Long id){
        super("Could not find item" + id);
    }

}
