package myApp.core.services.validators;


import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RemoveBankAccountValidatorTest {

    RemoveBankAccountValidator validator = new RemoveBankAccountValidator();

    @Test
    public void testSuccessValidate() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest("000000-00000");
        List<CoreError> errors = validator.validate(request);
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testShouldReturnErrorAboutWrongId() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(null);
        List<CoreError> errors = validator.validate(request);
        Assert.assertEquals("Field: Personal code", errors.get(0).getField());
        Assert.assertEquals("Personal code must not be empty", errors.get(0).getMessage());
    }
}