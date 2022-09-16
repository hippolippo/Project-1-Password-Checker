public class UnmatchedException extends InvalidPasswordException {
    UnmatchedException() {
        message = "The passwords do not match";
    }
}
