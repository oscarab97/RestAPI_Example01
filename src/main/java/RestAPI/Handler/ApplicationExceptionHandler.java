package RestAPI.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ApplicationExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clienteNotFoundException(ClienteNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hotelNotFoundException(HotelNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(HabitacionNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String habitacionNotFoundException(HabitacionNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ReservacionNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String reservacionNotFoundException(ReservacionNotFoundException ex) {
        return ex.getMessage();
    }
}
