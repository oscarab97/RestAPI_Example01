package RestAPI.Handler;

public class ReservacionNotFoundException extends RuntimeException{
    private Long id;
    public ReservacionNotFoundException(Long id){
        super("Could not find item" + id);
    }

}
