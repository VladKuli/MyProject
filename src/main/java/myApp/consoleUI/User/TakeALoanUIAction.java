package myApp.consoleUI.User;

import myApp.consoleUI.UIAction;
import myApp.core.requests.TakeALoanRequest;
import myApp.core.responses.TakeALoanResponse;
import myApp.core.services.TakeALoanService;
import myApp.core.services.authentication.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class TakeALoanUIAction implements UIAction {

    @Autowired
    private TakeALoanService service;
    @Autowired
    private UserService userService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String yourPersonalCode = userService.getPersonalCode();
        System.out.println("Enter value of loan: ");
        Integer valueOfLoan = scanner.nextInt();

        TakeALoanRequest request = new TakeALoanRequest(valueOfLoan);
        TakeALoanResponse response = service.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: "
                    + coreError.getField() + " " + coreError.getMessage()));
        } else if (response.isLoanSucceed()) {
            System.out.println("Loan successfully taken");
        } else {
            System.out.println("Error");
        }
    }
}