package myApp.core.services;


import junit.framework.TestCase;
import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class GetAllBankAccountsServiceTest {

    @Mock
    private JpaBankAccountRepository bankRepository;
    @InjectMocks
    private GetAllBankAccountsService service;

    @Test
   public void execute() {
        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        Mockito.when(bankRepository.findAll()).thenReturn(List.of(new BankAccount("Example", "Example",
                "000000-00001", null)));
        GetAllBankAccountsResponse response = service.execute(request);
        TestCase.assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        TestCase.assertEquals(response.getBankAccounts().get(0).getSurname(), "Example");
        TestCase.assertEquals(response.getBankAccounts().get(0).getPersonalCode(), "000000-00001");
        Mockito.verify(bankRepository,Mockito.times(1)).findAll();
    }
}