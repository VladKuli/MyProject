package myApp.core.services.validators;

import myApp.core.requests.Ordering;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OrderingValidatorTest {


    private OrderingValidator validator = new OrderingValidator();


    @Test
    public void testSuccessOrdering() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav", "Kulikov",
                "0000000-00001", new Ordering("surname", "ASCENDING"));
        List<CoreError> errors = validator.validateOrdering(request);
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testShouldReturnErrorAboutWrongOrderBy() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav", "Kulikov",
                "0000000-00001", new Ordering("", "ASCENDING"));
        List<CoreError> errors = validator.validateOrdering(request);
        Assert.assertEquals("Order by" ,errors.get(0).getField());
        Assert.assertEquals("Order by cannot be empty and " +
                "can only contain a 'name' or 'surname' or 'personal code'",errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutWrongOrderDirection() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav", "Kulikov",
                "0000000-00001", new Ordering("name", ""));
        List<CoreError> errors = validator.validateOrdering(request);
        Assert.assertEquals("Order direction" ,errors.get(0).getField());
        Assert.assertEquals("Order direction cannot be empty and" +
                " can only contain a 'ASCENDING' or 'DESCENDING'",errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutOrderByIsNull() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav", "Kulikov",
                "0000000-00001", new Ordering(null, "ASCENDING"));
        List<CoreError> errors = validator.validateOrdering(request);
        Assert.assertEquals("Order by" ,errors.get(0).getField());
        Assert.assertEquals("Must not be empty",errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutOrderDirectionIsNull() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav", "Kulikov",
                "0000000-00001", new Ordering("name", null));
        List<CoreError> errors = validator.validateOrdering(request);
        Assert.assertEquals("Order direction" ,errors.get(0).getField());
        Assert.assertEquals("Must not be empty",errors.get(0).getMessage());
    }
}