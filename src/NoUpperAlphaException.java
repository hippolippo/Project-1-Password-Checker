public class NoUpperAlphaException extends InvalidPasswordException {
    NoUpperAlphaException(){
        message = "The password must contain at least one uppercase alphabetic character";
    }
}
