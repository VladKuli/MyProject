package myApp.core.services.validators;

import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CloseAccountValidatorTest {

    private CloseAccountValidator validator = new CloseAccountValidator();

    @Test
    public void testShouldSuccess() {
        CloseAccountRequest request = new CloseAccountRequest("000000-00001");
        List<CoreError> errors = validator.validate(request);
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testShouldReturnErrorAboutWrongPersonalCode() {
        CloseAccountRequest request = new CloseAccountRequest("000000-01");
        List<CoreError> errors = validator.validate(request);
        Assert.assertEquals("Field: Personal code", errors.get(0).getField());
        Assert.assertEquals("Wrong Personal code, personal code must not be empty"
                , errors.get(0).getMessage());
    }
    @Test
    public void testShouldReturnErrorAboutEmptyPersonalCode() {
        CloseAccountRequest request = new CloseAccountRequest(null);
        List<CoreError> errors = validator.validate(request);
        Assert.assertEquals("Field: Personal code", errors.get(0).getField());
        Assert.assertEquals("Wrong Personal code, personal code must not be empty"
                , errors.get(0).getMessage());
    }
}