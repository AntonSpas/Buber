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

    /*@Test(dataProvider = "rightLinesDataProvider")
    public void shouldReturnTrueWhenRightData(String data) {
        boolean matches = BoatValidator.verifyBoat(data);
        Assert.assertTrue(matches);
    }

    @Test(dataProvider = "wrongLinesDataProvider")
    public void shouldReturnFalseWhenWrongData(String data) {
        boolean matches = BoatValidator.verifyBoat(data);
        Assert.assertFalse(matches);
    }*/
    /*
    private static final List<String> DATA_LIST = Arrays.asList(
            "100 0 ACHILLES",
            "150 150 BAUER",
            "80 100 CALYPSO",
            "120 60 DANAE");

    @Test
    public void shouldReturnRightBoatList() {
        Boat achilles = new Boat(100, 0, "ACHILLES");
        Boat bauer = new Boat(150, 150, "BAUER");
        Boat danae = new Boat(120, 60, "DANAE");
        List<Boat> expectedBoats = Arrays.asList(achilles, bauer, danae);

        BoatCreator boatCreator = new BoatCreator();
        List<Boat> actualBoats = boatCreator.createBoats(DATA_LIST);

        Assert.assertEquals(expectedBoats, actualBoats);
    }*/


/*
    private static final String WRONG_XML_FILE = "src/test/resources/wrong_gems.xml";
    private XMLValidator xmlValidator;

    @BeforeClass
    public void setup() {
        xmlValidator = new XMLValidator();
    }

    @Test
    public void shouldReturnTrueWhenValidateRightFile() throws GemsValidateException {
        boolean matches = xmlValidator.isValid(GemParser.XML_FILE);
        Assert.assertTrue(matches);
    }

    @Test(expectedExceptions = GemsValidateException.class)
    public void shouldThrowExceptionWhenValidateWrongFile() throws GemsValidateException {
        boolean matches = xmlValidator.isValid(WRONG_XML_FILE);
        Assert.assertTrue(matches);
    }*/



    /*@Test
    public void shouldReturnRightGemWhenParseXMLFile() throws ParseGemsException {
        GemSAXParser gemSAXParser = new GemSAXParser();
        List<AbstractGem> gems = gemSAXParser.parseListGems(GemParser.XML_FILE);

        DiamondColor expectedDiamondColor = ((Diamond) TestData.PUSHKIN).getDiamondColor();
        DiamondColor actualDiamondColor = ((Diamond) gems.get(0)).getDiamondColor();

        Assert.assertEquals(expectedDiamondColor, actualDiamondColor);
        asserts(gems.get(0), TestData.PUSHKIN);
        asserts(gems.get(1), TestData.ALLNATT);
        asserts(gems.get(2), TestData.ROCKEFELLER);
        asserts(gems.get(3), TestData.BISMARCK);
        asserts(gems.get(4), TestData.GRAFF);
        asserts(gems.get(5), TestData.ALAN);
        asserts(gems.get(6), TestData.CHALK);
        asserts(gems.get(7), TestData.MOGHUL);
    }

    private void asserts(AbstractGem actualGem, AbstractGem expectedGem) {
        String expectedName = expectedGem.getName();
        Preciousness expectedPreciousness = expectedGem.getPreciousness();
        String expectedOrigin  = expectedGem.getOrigin();
        Integer expectedClarity = expectedGem.getClarity();
        Integer expectedCuttingEdges = expectedGem.getCuttingEdges();
        Double expectedCarat = expectedGem.getCarat();

        String actualName = actualGem.getName();
        Preciousness actualPreciousness = actualGem.getPreciousness();
        String actualOrigin  = actualGem.getOrigin();
        Integer actualClarity = actualGem.getClarity();
        Integer actualCuttingEdges = actualGem.getCuttingEdges();
        Double actualCarat = actualGem.getCarat();

        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedPreciousness, actualPreciousness);
        Assert.assertEquals(expectedOrigin, actualOrigin);
        Assert.assertEquals(expectedClarity, actualClarity);
        Assert.assertEquals(expectedCuttingEdges, actualCuttingEdges);
        Assert.assertEquals(expectedCarat, actualCarat);
    }*/


    /*private static final String TEXT =
            "Typically, a controller coordinates with a dispatcher component. " +
                    "Dispatchers are responsible for view management and navigation. " +
                    "Thus, a dispatcher chooses the next view for the user and vectors " +
                    "control to the resource.";

    private static final String FIRST_SENTENCE =
            "Typically, a controller coordinates with a dispatcher component";
    private static final String SECOND_SENTENCE =
            "Dispatchers are responsible for view management and navigation";
    private static final String THIRD_SENTENCE =
            "Thus, a dispatcher chooses the next view for the user and vectors" +
                    " control to the resource";
    private static final String PUNCTUATION = ".";


    @Test
    public void shouldReturnTextWhenValid() {
        ParagraphComponent paragraphComponent = new ParagraphComponent();

        Component firstComponent = mock(Component.class);
        Component secondComponent = mock(Component.class);
        Component thirdComponent = mock(Component.class);
        Component punctuationComponent = mock(Component.class);

        paragraphComponent.addComponent(firstComponent);
        paragraphComponent.addComponent(punctuationComponent);
        paragraphComponent.addComponent(secondComponent);
        paragraphComponent.addComponent(punctuationComponent);
        paragraphComponent.addComponent(thirdComponent);
        paragraphComponent.addComponent(punctuationComponent);

        when(firstComponent.getStringRepresentation()).thenReturn(FIRST_SENTENCE);
        when(secondComponent.getStringRepresentation()).thenReturn(SECOND_SENTENCE);
        when(thirdComponent.getStringRepresentation()).thenReturn(THIRD_SENTENCE);
        when(punctuationComponent.getStringRepresentation()).thenReturn(PUNCTUATION);

        String actualText = paragraphComponent.getStringRepresentation();

        Assert.assertEquals(actualText, TEXT);

        Mockito.verify(firstComponent, only()).getStringRepresentation();
        Mockito.verify(secondComponent, only()).getStringRepresentation();
        Mockito.verify(thirdComponent, only()).getStringRepresentation();
        Mockito.verify(punctuationComponent, times(3))
                .getStringRepresentation();
        Mockito.verifyNoMoreInteractions(firstComponent, secondComponent,
                thirdComponent, punctuationComponent);
    }*/
}
