package com.example.ProjectBBVA.Handler;

public class ClientNotFoundException extends  RuntimeException{
    private Long id;
    public ClientNotFoundException(Long id){
        super("Could not find item" + id);
    }
}
