package myApp.core.services.validators;

import junit.framework.TestCase;
import myApp.core.requests.AddUserRequest;
import myApp.core.responses.CoreError;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
@Ignore
public class AddUserValidatorTest {


    private AddUserValidator validator = new AddUserValidator();

    @Test
    public void testShouldReturnErrorAboutName() {
        AddUserRequest request = new AddUserRequest("", "");
        List<CoreError> errors = validator.validate(request);
        TestCase.assertEquals("Field: Personal code ", errors.get(0).getField());
    }
}
