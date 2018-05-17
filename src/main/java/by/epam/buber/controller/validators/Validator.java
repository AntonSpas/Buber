package by.epam.buber.controller.validators;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Validator {
    boolean isValid(List<String> fields, HttpServletRequest request);
}
