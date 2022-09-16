public class PasswordException extends Exception{
    String message = "Bad Password";

    @Override
    public String toString(){
        return message;
    }

    public String getMessage(){
        return message;
    }
}

