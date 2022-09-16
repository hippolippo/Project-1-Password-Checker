public class NoSpecialCharacterException extends InvalidPasswordException {
    NoSpecialCharacterException() {
        message = "The password must contain at least one special character";
    }
}
