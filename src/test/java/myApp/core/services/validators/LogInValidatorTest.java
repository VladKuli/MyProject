package myApp.core.services.validators;

import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LogInValidatorTest {

    private LogInValidator validator = new LogInValidator();

    @Test
    public void testSuccessValidate() {
        LogInRequest request = new LogInRequest("000000-00001","password");
        List<CoreError> errors = validator.validate(request);
        Assert.assertTrue(errors.isEmpty());
    }


    @Test
    public void testShouldReturnErrorAboutLogin() {
        LogInRequest request = new LogInRequest("","password");
        List<CoreError> errors = validator.validate(request);
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals("Login",errors.get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutPassword() {
        LogInRequest request = new LogInRequest("000000-00001","");
        List<CoreError> errors = validator.validate(request);
        Assert.assertEquals("Password",errors.get(0).getField());
        Assert.assertEquals("Password cannot be empty",errors.get(0).getMessage());
    }
}