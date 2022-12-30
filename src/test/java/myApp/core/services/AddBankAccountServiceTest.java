package myApp.core.services;

import junit.framework.TestCase;
import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddBankAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddBankAccountServiceTest {

    @Mock
    private JpaBankAccountRepository bankRepository;
    @Mock
    private AddBankAccountValidator validator;
    @InjectMocks
    private AddBankAccountService service;


    @Test
    public void testShouldAddBankAccountToDataBase() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "Example"
                , "000000-00003");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        service.execute(request);
        Mockito.verify(bankRepository).save(new BankAccount("Example", "Example",
                "000000-00003", null));
    }

    @Test
    public void testShouldReturnResponseWithBankAccount() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "ExampleTwo"
                , "000000-00003");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        AddBankAccountResponse response = service.execute(request);
        TestCase.assertEquals(response.getBankAccount().getName(), "Example");
        TestCase.assertEquals(response.getBankAccount().getSurname(), "ExampleTwo");
        TestCase.assertEquals(response.getBankAccount().getPersonalCode(), "000000-00003");
    }

    @Test
    public void testShouldReturnErrorAboutName() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "ExampleTwo"
                , "000000-00003");
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Name",
                "Name can only contain letters and must not be empty")));
        AddBankAccountResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertEquals("Field: Name", response.getErrors().get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutSurname() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", null
                , "000000-00003");
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Surname",
                "Surname can only contain letters and must not be empty")));
        AddBankAccountResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertEquals("Field: Surname", response.getErrors().get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutNameAndSurname() {
        AddBankAccountRequest request = new AddBankAccountRequest(null, null,"000000-00003");
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Name",
                "Name can only contain letters and must not be empty"),
                new CoreError("Field: Surname",
                        "Surname can only contain letters and must not be empty")
        ));
        AddBankAccountResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertEquals("Field: Name", response.getErrors().get(0).getField());
        TestCase.assertEquals("Name can only contain letters and must not be empty",
                response.getErrors().get(0).getMessage());
        TestCase.assertEquals("Field: Surname", response.getErrors().get(1).getField());
        TestCase.assertEquals("Surname can only contain letters and must not be empty",
                response.getErrors().get(1).getMessage());
    }
}