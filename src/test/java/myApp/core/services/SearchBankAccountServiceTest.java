package myApp.core.services;

import junit.framework.TestCase;
import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.Ordering;
import myApp.core.requests.Paging;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.validators.SearchBankAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class SearchBankAccountServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;
    @Mock
    private SearchBankAccountValidator validator;
    @InjectMocks
    private SearchBankAccountService service;

    @Test
    public void testShouldSuccessFindByName() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "000000-00001", null )));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("Example", response.getBankAccounts().get(0).getName());
        Mockito.verify(bankAccountRepository).findByName("Example");
    }

    @Test
    public void testShouldSuccessFindBySurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "ExampleTwo", " ");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findBySurname("ExampleTwo")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        Mockito.verify(bankAccountRepository).findBySurname("ExampleTwo");
    }

    @Test
    public void testShouldSuccessFindByPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", " ", "000000-00001");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByPersonalCode("000000-00001")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("000000-00001",response.getBankAccounts().get(0).getPersonalCode());
        Mockito.verify(bankAccountRepository).findByPersonalCode("000000-00001");
    }

    @Test
    public void testShouldSuccessFindByNameAndSurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "ExampleTwo",
                " ");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByNameAndSurname("Example", "ExampleTwo"))
                .thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("Example",response.getBankAccounts().get(0).getName());
        TestCase.assertEquals("ExampleTwo",response.getBankAccounts().get(0).getSurname());
        Mockito.verify(bankAccountRepository).findByNameAndSurname("Example", "ExampleTwo");
    }

    @Test
    public void testShouldSuccessFindByNameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", "000000-00001");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByNameAndPersonalCode("Example", "000000-00001"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("Example", response.getBankAccounts().get(0).getName());
        TestCase.assertEquals("000000-00001", response.getBankAccounts().get(0).getPersonalCode());
        Mockito.verify(bankAccountRepository).findByNameAndPersonalCode("Example", "000000-00001");

    }

    @Test
    public void testFindBySurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("", "ExampleTwo", "000000-00001");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findBySurnameAndPersonalCode("ExampleTwo", "000000-00001"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        TestCase.assertEquals("000000-00001", response.getBankAccounts().get(0).getPersonalCode());
        Mockito.verify(bankAccountRepository).findBySurnameAndPersonalCode("ExampleTwo", "000000-00001");
    }

    @Test
    public void testFindByNameAndSurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "ExampleTwo", "000000-00001");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByNameAndSurnameAndPersonalCode("Example","ExampleTwo", "000000-00001"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("Example", response.getBankAccounts().get(0).getName());
        TestCase.assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        TestCase.assertEquals("000000-00001", response.getBankAccounts().get(0).getPersonalCode());
        Mockito.verify(bankAccountRepository).findByNameAndSurnameAndPersonalCode("Example", "ExampleTwo", "000000-00001");

    }

    @Test
    public void testFindByNameAndOrderByNameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("name", "ASCENDING"));
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByName("Example"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null),
                        new BankAccount("Example",
                                "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("Example",response.getBankAccounts().get(0).getName());
        Mockito.verify(bankAccountRepository).findByName("Example");
    }

    @Test
    public void testFindByNameAndOrderBySurnameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "", " ",
                new Ordering("surname", "DESCENDING"));
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "A", "000000-00001", null),
                new BankAccount("Example",
                        "B", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("B", response.getBankAccounts().get(0).getSurname());
        Mockito.verify(bankAccountRepository).findByName("Example");
    }

    @Test
    public void testFindByNameAndOrderByPersonalCodeWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "DESCENDING"));
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null),
                new BankAccount("Example",
                        "ExampleTwo", "000000-00002", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("000000-00002",response.getBankAccounts().get(0).getPersonalCode());
        Mockito.verify(bankAccountRepository).findByName("Example");
    }

    @Test
    public void testShouldReturnTwoBankAccountPaging() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "ASCENDING"), new Paging(1, 1));
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(bankAccountRepository.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null),
                new BankAccount("Example",
                        "ExampleTwo", "000000-00002", null)));
        SearchBankAccountResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        TestCase.assertEquals("000000-00001",response.getBankAccounts().get(0).getPersonalCode());
        Mockito.verify(bankAccountRepository).findByName("Example");
    }
}