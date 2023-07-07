package tech.kbtg.springboothomework.Entity.Exception;

public class BadRequest extends RuntimeException{
    public BadRequest(String message )
    {
        super( message );
    }
}
