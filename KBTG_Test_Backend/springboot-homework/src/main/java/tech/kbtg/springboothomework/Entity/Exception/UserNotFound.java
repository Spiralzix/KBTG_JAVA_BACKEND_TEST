package tech.kbtg.springboothomework.Entity.Exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message )
    {
        super( message );
    }
}
