package by.epam.buber.controller.validators;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DriverSignUpValidatorTest {
    private DriverSignUpValidator validator;
    private HttpServletRequest request;
    private HttpSession session;

    @BeforeClass
    public void setup() {
        validator = new DriverSignUpValidator();
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        doReturn("en").when(session).getAttribute("language");
    }

    @Test(dataProvider = "rightLinesDataProvider")
    public void shouldReturnTrueWhenCorrectFields(List<String> fields) {
        boolean isValid = validator.isValid(fields, request);
        Assert.assertTrue(isValid);
    }

    @Test(dataProvider = "wrongLinesDataProvider")
    public void shouldReturnFalseWhenIncorrectFields(List<String> fields) {
        boolean isValid = validator.isValid(fields, request);
        Assert.assertFalse(isValid);
    }

    @DataProvider(name = "rightLinesDataProvider")
    public Object[][] rightLinesDataProvider() {
        return new Object[][]{
                {Arrays.asList("Василий", "Деревяшкин", "user@domain.com", "1101gqh",
                        "+375(44)111-22-33", "ECONOMY", "Kia Rio", "1111 AA-7")},
                {Arrays.asList("Fransua", "Leberge", "user_name@domain.co", "1101gqh",
                        "+375(44)111-22-33", "PREMIUM",
                        "Opel Insignia Sports Tourer 2.0 BiTurbo CDTI ecoFlex", "1111 AA-7")}
        };
    }

    @DataProvider(name = "wrongLinesDataProvider")
    public Object[][] wrongLinesDataProvider() {
        return new Object[][]{
                {Arrays.asList("George1", "Oruell", "user@domain.co", "1101gqh",
                        "+375(44)111-11-11", "ECONOMY", "Kia Rio", "1111 AA-7")},
                {Arrays.asList("George", "Oruell1", "user@domain.co", "1101gqh",
                        "+375(44)111-11-11", "ECONOMY", "Kia Rio", "1111 AA-7")},
                {Arrays.asList("George", "Oruell", "user@domain.c", "1101gqh",
                        "+375(44)111-11-11", "ECONOMY", "Kia Rio", "1111 AA-7")},
                {Arrays.asList("George", "Oruell", "user@domain.co", "1101g",
                        "+375(44)111-11-11", "ECONOMY", "Kia Rio", "1111 AA-7")},
                {Arrays.asList("George", "Oruell", "user@domain.co", "1101gqh",
                        "+375(45)111-11-11", "ECONOMY", "Kia Rio", "1111 AA-7")},
                {Arrays.asList("George", "Oruell", "user@domain.co", "1101gqh",
                        "+375(44)111-11-11", "ECONOM", "Kia Rio", "1111 AA-7")},
                {Arrays.asList("George", "Oruell", "user@domain.co", "1101gqh",
                        "+375(44)111-11-11", "ECONOMY", "Kia", "1111 AA-7")},
                {Arrays.asList("George", "Oruell", "user@domain.co", "1101gqh",
                        "+375(44)111-11-11", "ECONOMY", "Kia Rio", "1111 AA-8")}
        };
    }
}
