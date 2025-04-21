package filipeProject.example.authenticationJwt.exceptions;


public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(String message){
        super(message);
    }
}
