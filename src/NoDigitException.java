public class NoDigitException extends InvalidPasswordException {
    NoDigitException() {
        message = "The password must contain at least one digit";
    }
}
