package myApp.core.services;

import junit.framework.TestCase;
import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.CloseAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class CloseAccountServiceTest {

    @Mock
    private JpaBankAccountRepository bankRepository;
    @Mock
    private CloseAccountValidator validator;
    @InjectMocks
    private CloseAccountService service;


    @Test
    public void testCloseAccountWithoutErrors() {
        CloseAccountRequest request = new CloseAccountRequest("000000-00001");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        CloseAccountResponse response = service.execute(request);
        Mockito.verify(bankRepository, Mockito.times(0)).closeAccount("000000-00001");
    }

    @Test
    public void testCloseAccountWithErrors() {
        CloseAccountRequest request = new CloseAccountRequest(null);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Personal code",
                "Personal code must not be empty")));
        CloseAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.isDeleted());
        TestCase.assertEquals("Field: Personal code", response.getErrors().get(0).getField());
        TestCase.assertEquals("Personal code must not be empty", response.getErrors().get(0).getMessage());
        Mockito.verify(bankRepository, Mockito.times(0)).closeAccount(null);
    }
}