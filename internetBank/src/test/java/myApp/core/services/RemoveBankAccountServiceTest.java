package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.validators.RemoveBankAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class RemoveBankAccountServiceTest {

    @Mock
    JpaBankAccountRepository bankRepository;
    @Mock
    RemoveBankAccountValidator validator;
    @InjectMocks
    RemoveBankAccountService service;

    @Test
    public void testSuccessRemoveBankAccount() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest("000000-00000");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        RemoveBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertTrue(response.isDeleted());
        Mockito.verify(bankRepository).deleteByPersonalCode("000000-00000");
    }

    @Test
    public void testShouldReturnPersonalCodeError() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(null);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Personal code",
                "Personal code must not be empty")));
        RemoveBankAccountResponse response = service.execute(request);
        Mockito.verify(bankRepository, Mockito.times(0)).deleteByPersonalCode(null);
    }
}