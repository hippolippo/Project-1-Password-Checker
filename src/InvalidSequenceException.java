public class InvalidSequenceException extends InvalidPasswordException {
    InvalidSequenceException() {
        message = "The password cannot contain more than two of the same character in sequence";
    }
}
