public class NoLowerAlphaException extends InvalidPasswordException {
    NoLowerAlphaException() {
        message = "The password must contain at least one lowercase alphabetic character";
    }
}
