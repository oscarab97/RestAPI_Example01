package com.example.ProjectBBVA.Services;

import com.example.ProjectBBVA.Handler.BookingNotFoundException;
import com.example.ProjectBBVA.Model.Booking;
import com.example.ProjectBBVA.Persistences.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired // inyecta hacia el repositorio
    BookingRepository bookingRepository;

    public List<Booking> BookingFindAll(){
        return bookingRepository.findAll();
    }
    public Booking BookingFindById(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }
    public Booking BookingSave(Booking new_booking){
        return bookingRepository.save(new_booking);
    }
    public String BookingDelete(Long id){
        return bookingRepository.findById(id)
                .map(booking->{
                    booking.setState(false);
                    bookingRepository.save(booking);
                    return "Booking eliminado";
                })
                .orElseGet(()->{
                    return  "no se pudo eliminar el Booking";
                });
    }

    public Booking BookingkUpdate(Booking newBooking, Long id) {

        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setCheck_In(newBooking.getCheck_In());
                    booking.setCheck_out(newBooking.getCheck_out());
                    booking.setState(newBooking.isState());
                    booking.setClient(newBooking.getClient());
                    booking.setRooms(newBooking.getRooms());
                    booking.setNum_Person(newBooking.getNum_Person());
                    return bookingRepository.save(booking);
                })
                .orElseGet(() -> {
                    newBooking.setId(id);
                    return bookingRepository.save(newBooking);
                });
    }
}
