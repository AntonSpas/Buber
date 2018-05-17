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

public class AdminLoginValidatorTest {
    private AdminLoginValidator validator;
    private HttpServletRequest request;
    private HttpSession session;

    @BeforeClass
    public void setup() {
        validator = new AdminLoginValidator();
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
                {Arrays.asList("admin3", "1101gqh")},
                {Arrays.asList("7070admin", "Graph+_/007")}
        };
    }

    @DataProvider(name = "wrongLinesDataProvider")
    public Object[][] wrongLinesDataProvider() {
        return new Object[][]{
                {Arrays.asList("adm", "1101gqh")},
                {Arrays.asList("7070admin", "Graph")}
        };
    }
}
