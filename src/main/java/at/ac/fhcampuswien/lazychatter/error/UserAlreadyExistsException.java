package at.ac.fhcampuswien.lazychatter.error;

public class UserAlreadyExistsException extends IllegalStateException{
    public UserAlreadyExistsException(){
        this("User was already defined");
    }

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
