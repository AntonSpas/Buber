package by.epam.buber.controller.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginValidator extends AbstractValidator{

    private static final String EMAIL_PATTERN = "^[\\w._-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*\\d)\\S{6,16}$";

    @Override
    public boolean isValid(List<String> fields, HttpServletRequest request) {
        String message = "";
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        Locale locale =  new Locale(language);
        ResourceBundle bundle = ResourceBundle.getBundle("localization", locale);
        boolean result = true;

        String email = fields.get(0);
        if (!matches(email, EMAIL_PATTERN)) {
            String messageAddition = bundle.getString("wrong_email");
            message = message.concat(messageAddition);
            result = false;
        }

        String password = fields.get(1);
        if (!matches(password, PASSWORD_PATTERN)) {
            String messageAddition = bundle.getString("wrong_password");
            message = message.concat(messageAddition);
            result = false;
        }

        session.setAttribute("isValid", result);
        session.setAttribute("message", message);

        return result;
    }
}
