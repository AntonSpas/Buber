package by.epam.buber.controller.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminLoginValidator extends AbstractValidator{

    private static final String LOGIN_PATTERN = "^(?=.*[a-z])\\S{4,16}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*\\d)\\S{6,16}$";

    @Override
    public boolean isValid(List<String> fields, HttpServletRequest request) {
        String message = "";
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        Locale locale =  new Locale(language);
        ResourceBundle bundle = ResourceBundle.getBundle("localization", locale);
        boolean result = true;

        String login = fields.get(0);
        if (!matches(login, LOGIN_PATTERN)) {
            String messageAddition = bundle.getString("wrong_login");
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
