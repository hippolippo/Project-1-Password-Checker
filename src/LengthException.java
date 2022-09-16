public class LengthException extends InvalidPasswordException {
    LengthException() {
        message = "The password must be at least 6 characters long";
    }
}
