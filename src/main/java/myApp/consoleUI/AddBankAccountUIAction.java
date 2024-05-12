package myApp.consoleUI;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.AddUserRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.AddUserResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.services.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Scanner;

@Component
public class AddBankAccountUIAction implements UIAction {
    @Autowired
    private AddBankAccountService service;
    @Autowired
    private AddUserService userService;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login: ");
        String login = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter personal code: ");
        String personalCode = scanner.nextLine();
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        String encodedLogin = Base64.getEncoder().encodeToString(login.getBytes());


        AddUserRequest userRequest = new AddUserRequest(encodedLogin, encodedPassword);//
        AddUserResponse responseUser = userService.execute(userRequest);

        AddBankAccountRequest request = new AddBankAccountRequest(name, surname, personalCode);
        AddBankAccountResponse response = service.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: "
                    + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.getBankAccount() != null) {
                System.out.println("Bank account has been added");
            } else {
                System.out.println("Bank account has not been added");
            }
        }
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: "
                    + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (responseUser.getUser() != null) {
                System.out.println("User has been added");
            } else {
                System.out.println("User has not been added");
            }
        }
    }
}