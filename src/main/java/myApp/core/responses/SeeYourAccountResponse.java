package myApp.core.responses;

import myApp.core.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public class SeeYourAccountResponse extends CoreResponse {

    private Optional<BankAccount> bankAccount;
}
