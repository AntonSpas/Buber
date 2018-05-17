package by.epam.buber.controller.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractValidator implements Validator {

    protected boolean matches(String text, String target) {
        if (text == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(target);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
