package myApp.core.services.validators;

import myApp.core.requests.Paging;
import myApp.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PagingValidatorTest {

    private PagingValidator validator = new PagingValidator();

    @Test
    public void testShouldReturnErrorWhenPageNumberIsZero() {
        Paging paging = new Paging(0,1);
        List<CoreError> errors = validator.validate(paging);
        Assert.assertEquals(1, errors.size());
        Assert.assertEquals(errors.get(0).getField(), "Page number");
        Assert.assertEquals(errors.get(0).getMessage(), "Must be greater then 0");
    }

    @Test
    public void testShouldReturnErrorWhenPageSizeIsZero() {
        Paging paging = new Paging(1, 0);
        List<CoreError> errors = validator.validate(paging);
        Assert.assertEquals(1, errors.size());
        Assert.assertEquals(errors.get(0).getField(), "Page size");
        Assert.assertEquals(errors.get(0).getMessage(), "Must be greater then 0");
    }

    @Test
    public void testShouldReturnErrorWhenPageNumberIsNull() {
        Paging paging = new Paging(null, 1);
        List<CoreError> errors = validator.validate(paging);
        Assert.assertEquals(1, errors.size());
        Assert.assertEquals(errors.get(0).getField(), "Page number");
        Assert.assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void testShouldReturnErrorWhenPageSizeIsNull() {
        Paging paging = new Paging(1, null);
        List<CoreError> errors = validator.validate(paging);
        Assert.assertEquals(1, errors.size());
        Assert.assertEquals(errors.get(0).getField(), "Page size");
        Assert.assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

}