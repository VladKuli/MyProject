package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.requests.TakeALoanRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.TakeALoanResponse;
import myApp.core.services.validators.TakeALoanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class TakeALoanService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private TakeALoanValidator validator;

    public TakeALoanResponse execute(TakeALoanRequest request) {
        List<CoreError> errors = validator.execute(request);
        if (errors.isEmpty()) {
            bankAccountRepository.takeALoan(getUsername(), request.getValue());
            return new TakeALoanResponse(true);
        } else {
            return new TakeALoanResponse(errors);
        }
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}