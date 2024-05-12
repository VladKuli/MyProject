package myApp.core.services;

import junit.framework.TestCase;
import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddBankAccountValidator;
import myApp.matcher.BankAccountMatcher;
import org.h2.command.dml.MergeUsing;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddBankAccountServiceTest {

    @Mock
    private JpaBankAccountRepository repository;
    @Mock
    private AddBankAccountValidator validator;
    @InjectMocks
    private AddBankAccountService service;


    @Test
    public void addBankAccountSuccessfully() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "000000-00006");
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "Example", "000000-00006");
        service.execute(request);
        Mockito.verify(repository, Mockito.times(1)).save(bankAccount);
    }

    @Test
    public void shouldReturnExceptionAboutName() {
        BankAccount bankAccount = new BankAccount(null, "Example", "000000-00006");
        AddBankAccountRequest request = new AddBankAccountRequest(null, "Example", "000000-00006");
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Name",
                "Must not be empty")));
        service.execute(request);
        Mockito.verify(repository, Mockito.times(0)).save(bankAccount);
    }

    @Test
    public void shouldReturnExceptionAboutSurname() {
        BankAccount bankAccount = new BankAccount("Example", null, "000000-00006");
        AddBankAccountRequest request = new AddBankAccountRequest("Example", null, "000000-00006");
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Surname",
                "Must not be empty")));
        service.execute(request);
        Mockito.verify(repository, Mockito.times(0)).save(bankAccount);
    }
    @Test
    public void shouldReturnExceptionAboutPersonalCode() {
        BankAccount bankAccount = new BankAccount("Example", "Example", null);
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "Example", null);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Personal Code",
                "Must not be empty")));
        service.execute(request);
        Mockito.verify(repository, Mockito.times(0)).save(bankAccount);
    }
    @Test
    public void shouldNotAddWithExceptionsAboutAllStatements() {
        BankAccount bankAccount = new BankAccount(null, null, null);
        AddBankAccountRequest request = new AddBankAccountRequest(null, null, null);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Name",
                "Must not be empty"), new CoreError("Surname",
                "Must not be empty"),new CoreError("Personal Code",
                "Must not be empty")));
        service.execute(request);
        Mockito.verify(repository, Mockito.times(0)).save(bankAccount);
    }
}