package by.epam.buber.controller.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DriverSignUpValidator extends AbstractValidator{

    private static final String NAME_PATTERN = "^[А-ЯЁЎІ][а-яёўі']*|[A-Z][a-z]*$";
    private static final String EMAIL_PATTERN = "^[\\w._-]+@[\\w.-]+\\.[a-zA-Z]{2,3}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*\\d)\\S{6,16}$";
    private static final String PHONE_PATTERN = "^\\+375\\((29|33|44|25)\\)\\d{3}-\\d{2}-\\d{2}$";
    private static final String CAR_TYPE_PATTERN = "^ECONOMY|PREMIUM|LARGE$";
    private static final String CAR_MODEL_PATTERN = "^[a-zA-Z]{2,16}( \\p{Graph}+)+$";
    private static final String CAR_NUMBER_PATTERN = "^\\d{4} [A-Z]{2}-[1-7]$";

    @Override
    public boolean isValid(List<String> fields, HttpServletRequest request) {
        String message = "";
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        Locale locale =  new Locale(language);
        ResourceBundle bundle = ResourceBundle.getBundle("localization", locale);
        boolean result = true;

        String name = fields.get(0);
        if (!matches(name, NAME_PATTERN)) {
            String messageAddition = bundle.getString("wrong_name");
            message = message.concat(messageAddition);
            result = false;
        }

        String surname = fields.get(1);
        if (!matches(surname, NAME_PATTERN)) {
            String messageAddition = bundle.getString("wrong_surname");
            message = message.concat(messageAddition);
            result = false;
        }

        String email = fields.get(2);
        if (!matches(email, EMAIL_PATTERN)) {
            String messageAddition = bundle.getString("wrong_email");
            message = message.concat(messageAddition);
            result = false;
        }

        String password = fields.get(3);
        if (!matches(password, PASSWORD_PATTERN)) {
            String messageAddition = bundle.getString("wrong_password");
            message = message.concat(messageAddition);
            result = false;
        }

        String phone = fields.get(4);
        if (!matches(phone, PHONE_PATTERN)) {
            String messageAddition = bundle.getString("wrong_phone");
            message = message.concat(messageAddition);
            result = false;
        }

        String carType = fields.get(5);
        if (!matches(carType, CAR_TYPE_PATTERN)) {
            String messageAddition = bundle.getString("wrong_car_type");
            message = message.concat(messageAddition);
            result = false;
        }

        String carModel = fields.get(6);
        if (!matches(carModel, CAR_MODEL_PATTERN)) {
            String messageAddition = bundle.getString("wrong_car_model");
            message = message.concat(messageAddition);
            result = false;
        }

        String carNumber = fields.get(7);
        if (!matches(carNumber, CAR_NUMBER_PATTERN)) {
            String messageAddition = bundle.getString("wrong_car_number");
            message = message.concat(messageAddition);
            result = false;
        }

        session.setAttribute("isValid", result);
        session.setAttribute("message", message);

        return result;
    }
}
