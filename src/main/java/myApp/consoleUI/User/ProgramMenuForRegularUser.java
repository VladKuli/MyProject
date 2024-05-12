package myApp.consoleUI.User;

import myApp.consoleUI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

//@Component
public class ProgramMenuForRegularUser {

    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenuForRegularUser(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, MoneyTransferUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, OpenAccountUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, CloseAccountUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, SeeYourAccountUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, TakeALoanUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, SwitchUserUIAction.class));
    }

     public void printInformationForRegularUser() {
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1 - Transfer money");
        System.out.println("2 - Open an account");
        System.out.println("3 - Close an account");
        System.out.println("4 - See your account");
        System.out.println("5Take a loan");
        System.out.println("6 - Switch user");
        System.out.println("7 - Exit");
    }

    public int userChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        return scanner.nextInt();
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        for (UIAction uiAction : uiActions) {
            if (uiAction.getClass().equals(uiActionClass)) {
                return Optional.of(uiAction)
                        .get();
            }
        }
        return Optional.<UIAction>empty()
                .get();
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}