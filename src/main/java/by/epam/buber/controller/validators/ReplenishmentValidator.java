package by.epam.buber.controller.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReplenishmentValidator extends AbstractValidator{

    private static final String MONEY_AMOUNT_PATTERN = "^[0-9]*(\\.[0-9]*)?$";

    @Override
    public boolean isValid(List<String> fields, HttpServletRequest request) {
        String message = "";
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        Locale locale =  new Locale(language);
        ResourceBundle bundle = ResourceBundle.getBundle("localization", locale);
        boolean result = true;

        String money_amount = fields.get(0);
        if (!matches(money_amount, MONEY_AMOUNT_PATTERN)) {
            String messageAddition = bundle.getString("wrong_money_amount");
            message = message.concat(messageAddition);
            result = false;
        }

        session.setAttribute("isValid", result);
        session.setAttribute("message", message);

        return result;
    }
}
