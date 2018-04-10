package appp.renthub;

import android.util.Patterns;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.view.View.Z;

/**
 * Created by PULKITCHANDRA on 02-Apr-18.
 */

public class Validation {
    public static boolean isValidName( String name) {
        String NAME_PATTERN = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();

    }
    public static boolean isValidPassword(String password){
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-zA-Z]).{8,20})";

        return PASSWORD_PATTERN.matches(password);

    }
    public static boolean isValidEmail(String mail) {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }


    public static boolean isValidPhone(String target) {
        if (target == null || target.length() < 10 || target.length() > 10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
}
