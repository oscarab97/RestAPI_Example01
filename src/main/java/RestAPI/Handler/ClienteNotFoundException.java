package RestAPI.Handler;

public class ClienteNotFoundException extends  RuntimeException{
    private Long id;
    public ClienteNotFoundException(Long id){
        super("Could not find item" + id);
    }

}
