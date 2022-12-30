package myApp.core.services;

import junit.framework.TestCase;
import myApp.core.database.BankRepository;
import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.LogInResponse;
import myApp.core.services.authentication.LogInService;
import myApp.core.services.authentication.UserService;
import myApp.core.services.validators.LogInValidator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@Ignore
@RunWith(MockitoJUnitRunner.class)
public class LogInServiceTest {

    @Mock
    private BankRepository bankRepository;
    @Mock
    private LogInValidator validator;
    @Mock
    private UserService userService;
    @InjectMocks
    private LogInService service;

    @Test
    public void testSuccessLogIn() {
        LogInRequest request = new LogInRequest("111-111", "password");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(userService.logIn("111-111", "password")).thenReturn(true);
        LogInResponse response = service.execute(request);
        TestCase.assertFalse(response.hasErrors());
        Mockito.verify(userService).logIn("111-111", "password");
        TestCase.assertEquals("111-111", response.getPersonalCode());
    }

    @Test
    public void testShouldReturnPersonalCodeError() {
        LogInRequest request = new LogInRequest(null, "password");
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Personal code",
                "Personal code may contains only numbers and cannot be empty")));
        LogInResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertEquals("Personal code", response.getErrors().get(0).getField());
        TestCase.assertEquals("Personal code may contains only numbers and cannot be empty",
                response.getErrors().get(0).getMessage());
    }

    @Test
    public void testShouldReturnPasswordError() {
        LogInRequest request = new LogInRequest("111-111", null);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Password",
                "cannot be empty")));
        LogInResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertEquals("Password", response.getErrors().get(0).getField());
        TestCase.assertEquals("cannot be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void testShouldReturnPersonalCodeAndPasswordErrors() {
        LogInRequest request = new LogInRequest(null, null);
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("Password",
                "cannot be empty"), new CoreError("Personal code",
                "Personal code may contains only numbers and cannot be empty")));
        LogInResponse response = service.execute(request);
        TestCase.assertTrue(response.hasErrors());
        TestCase.assertEquals("Personal code", response.getErrors().get(1).getField());
        TestCase.assertEquals("Personal code may contains only numbers and cannot be empty",
                response.getErrors().get(1).getMessage());
        TestCase.assertEquals("Password", response.getErrors().get(0).getField());
        TestCase.assertEquals("cannot be empty", response.getErrors().get(0).getMessage());
    }
}