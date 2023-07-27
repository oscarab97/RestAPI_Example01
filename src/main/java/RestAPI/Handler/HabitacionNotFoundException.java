package RestAPI.Handler;

public class HabitacionNotFoundException extends RuntimeException{
    private Long id;
    public HabitacionNotFoundException(Long id){
        super("Could not find item" + id);
    }

}
