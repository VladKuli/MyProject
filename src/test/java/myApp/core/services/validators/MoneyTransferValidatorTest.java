package myApp.core.services.validators;

import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class MoneyTransferValidatorTest {

    private MoneyTransferValidator validator = new MoneyTransferValidator();

    @Test
    public void shouldGiveSuccessResult() {
        MoneyTransferRequest request = new MoneyTransferRequest("000000-00002", 100);
        List<CoreError> errors = validator.validate(request);
        Assertions.assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnExceptionAboutPersonalCode() {
        MoneyTransferRequest request = new MoneyTransferRequest(null, 100);
        List<CoreError> errors = validator.validate(request);
        Assertions.assertFalse(errors.isEmpty());
    }

    @Test
    public void shouldReturnExceptionAboutMoneyValue() {
        MoneyTransferRequest request = new MoneyTransferRequest("000000-00002", 0);
        List<CoreError> errors = validator.validate(request);
        Assertions.assertFalse(errors.isEmpty());
    }

    @Test
    public void shouldReturnExceptionAboutAllStatements() {
        MoneyTransferRequest request = new MoneyTransferRequest(null, 0);
        List<CoreError> errors = validator.validate(request);
        Assertions.assertEquals(2, errors.size());
    }
}