package myApp.core.services;

import junit.framework.TestCase;
import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.validators.OpenAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OpenAccountServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;
    @Mock
    private OpenAccountValidator validator;
    @InjectMocks
    private OpenAccountService service;

    @Test
    public void testSuccessOpenAccount() {
        OpenAccountRequest request = new OpenAccountRequest("000000-00001");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        OpenAccountResponse response = service.execute(request);
        Mockito.verify(bankAccountRepository, Mockito.times(0)).openAccount("000000-00001");
    }

    @Test
    public void testShouldReturnPersonalCodeError() {
        OpenAccountRequest request = new OpenAccountRequest(null);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Personal code",
                "Personal code must not be empty")));
        OpenAccountResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertFalse(response.isCompleted());
        Mockito.verify(bankAccountRepository, Mockito.times(0)).openAccount(null);
        TestCase.assertEquals("Field: Personal code",response.getErrors().get(0).getField());
        TestCase.assertEquals("Personal code must not be empty", response.getErrors().get(0).getMessage());

    }
}