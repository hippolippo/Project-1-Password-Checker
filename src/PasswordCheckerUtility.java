import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {

    //Functions to satisfy test cases:
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
        boolean result = checkUpperAlpha(password);
        if(!result)
            throw new NoUpperAlphaException();
        return true;
    }
    public static boolean isValidLength(String password) throws LengthException{
        boolean result = checkPasswordLength(password);
        if(!result)
            throw new LengthException();
        return true;
    }
    public static boolean comparePasswordsWithReturn(String passwordString, String passwordAString) {
        return passwordString.equals(passwordAString);
    }
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
        if(comparePasswordsWithReturn(password, passwordConfirm)){
            throw(new UnmatchedException());
        }
    }
    public static boolean isWeakPassword(String passwordString) throws WeakPasswordException {
        try{
            isValidPassword(passwordString);
        }catch(InvalidPasswordException e){
            throw(new WeakPasswordException());
        }
        if(match(passwordString, "^.{6,9}$"))
            throw(new WeakPasswordException());
        return false;
    }
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> results = new ArrayList<>();
        for(String i : passwords){
            try{
                isValidPassword(i);
            }catch(InvalidPasswordException e){
                results.add(i + " " + e.getMessage());
            }
        }
        return results;
    }
    public static boolean isValidPassword(String passwordString) throws InvalidPasswordException {
        if(!checkPasswordLength(passwordString))
            throw(new LengthException());
        if(!checkUpperAlpha(passwordString))
            throw(new NoUpperAlphaException());
        if(!checkLowerAlpha(passwordString))
            throw(new NoLowerAlphaException());
        if(!checkHasNumber(passwordString))
            throw(new NoDigitException());
        if(!checkSpecialCharacter(passwordString))
            throw(new NoSpecialCharacterException());
        if(!checkSequence(passwordString))
            throw(new InvalidSequenceException());
        return true;
    }
    private static boolean match(String password, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return (matcher.matches());
    }
    private static boolean checkPasswordLength(String password){
        return match(password, ".{6}.*");
    }
    private static boolean checkUpperAlpha(String password){
        return match(password, ".*[A-Z].*");
    }
    private static boolean checkLowerAlpha(String password){
        return match(password, ".*[a-z].*");
    }
    private static boolean checkHasNumber(String password){
        return match(password, ".*[0-9].*");
    }
    private static boolean checkSpecialCharacter(String password) {
        return match(password, ".*[^a-zA-Z0-9].*");
    }
    private static boolean checkSequence(String password) {
        return !match(password, ".*(.)\\1\\1.*");
    }
}
