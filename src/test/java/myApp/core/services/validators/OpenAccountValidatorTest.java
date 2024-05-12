package myApp.core.services.validators;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class OpenAccountValidatorTest {

    private OpenAccountValidator validator = new OpenAccountValidator();

    @Test
    public void testSuccessValidate() {
        OpenAccountRequest request = new OpenAccountRequest("000000-00001");
        List<CoreError> errors = validator.validate(request);
        Assert.assertTrue(errors.isEmpty());
    }



    @Test
    public void testShouldReturnErrorAboutEmptyPersonalCode() {
        OpenAccountRequest request = new OpenAccountRequest("");
        List<CoreError> errors = validator.validate(request);
        Assertions.assertEquals(1, errors.size());
    }
}