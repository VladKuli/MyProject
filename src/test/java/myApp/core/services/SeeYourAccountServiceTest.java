package myApp.core.services;

import junit.framework.TestCase;
import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.SeeYourAccountResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class SeeYourAccountServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;
    @InjectMocks
    private SeeYourAccountService service;

    @Test
    public void testSeeAccount() {
        SeeYourAccountRequest request = new SeeYourAccountRequest("000000-00002");
        Optional<BankAccount> bankAccount = Optional.of(new BankAccount(
                "Example", "","000000-00002",0));
        Mockito.when(bankAccountRepository.seeYourAccount("000000-00002"))
                .thenReturn(bankAccount);
        service.execute(request);
        Mockito.verify(bankAccountRepository, Mockito.times(1))
                .seeYourAccount("000000-00002");
    }
}