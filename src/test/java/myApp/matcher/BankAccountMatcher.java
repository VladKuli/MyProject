package myApp.matcher;

import lombok.AllArgsConstructor;
import myApp.core.domain.BankAccount;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class BankAccountMatcher implements ArgumentMatcher<BankAccount> {

    private String name;
    private String surname;
    private String personalCode;

    @Override
    public boolean matches(BankAccount bankAccount) {
        return bankAccount.getName().equals(name) &&
                bankAccount.getSurname().equals(surname) &&
                bankAccount.getPersonalCode().equals(personalCode);
    }
}