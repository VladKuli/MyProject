package myApp.core.services.validators;

import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MoneyTransferValidatorTest {

    private MoneyTransferValidator validator = new MoneyTransferValidator();

    @Test
    public void testSuccessValidate() {
        MoneyTransferRequest request = new MoneyTransferRequest(
                "000000-00002", 100);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testShouldReturnErrorEmptyPersonalCodeAboutAnotherPersonalCode() {
        MoneyTransferRequest request = new MoneyTransferRequest(
                "", 100);
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Another personal code", errors.get(0).getField());
        assertEquals("Another personal code must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutValue() {
        MoneyTransferRequest request = new MoneyTransferRequest(
                "000000-00002", 0);
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Value", errors.get(0).getField());
        assertEquals("Value must not be empty, and must be bigger than 0", errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutAllFields() {
        MoneyTransferRequest request = new MoneyTransferRequest("", 0);
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Value", errors.get(1).getField());
        assertEquals("Value must not be empty, and must be bigger than 0", errors.get(1).getMessage());
    }

}