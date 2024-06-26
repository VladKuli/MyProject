package myApp.core.services;

import junit.framework.TestCase;
import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.validators.MoneyTransferValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MoneyTransferServiceTest {

    @Mock
    private JpaBankAccountRepository bankRepository;
    @Mock
    private MoneyTransferValidator validator;
    @InjectMocks
    private MoneyTransferService service;

    @Test
    public void testSuccessMoneyTransfer() {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken("000000-00002", "pass");
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authReq);

        MoneyTransferRequest request = new MoneyTransferRequest("000000-00001", 100);
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        MoneyTransferResponse response = service.execute(request);
        Mockito.verify(bankRepository).bankTransfer("000000-00002",
                "000000-00001", 100);
    }

    @Test
    public void testShouldReturnValueError() {
        MoneyTransferRequest request = new MoneyTransferRequest("000000-00002", 0);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Value",
                "Value must not be empty, and must be bigger than 0")));
        MoneyTransferResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertEquals(1, response.getErrors().size());
        TestCase.assertEquals("Field: Value",
                response.getErrors().get(0).getField());
        TestCase.assertEquals("Value must not be empty, and must be bigger than 0",
                response.getErrors().get(0).getMessage());
    }

    @Test
    public void testShouldReturnAllErrors() {
        MoneyTransferRequest request = new MoneyTransferRequest("",  0);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Another personal code",
                "Another personal code must not be empty"),new CoreError("Field: Value",
                "Value must not be empty, and must be bigger than 0")));
        MoneyTransferResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertEquals(2, response.getErrors().size());
        TestCase.assertEquals("Field: Value",
                response.getErrors().get(1).getField());
        TestCase.assertEquals("Value must not be empty, and must be bigger than 0",
                response.getErrors().get(1).getMessage());
    }
}

