package myApp.core.responses;


import lombok.Getter;
import myApp.core.domain.BankAccount;

import java.util.List;
@Getter
public class AddBankAccountResponse extends CoreResponse {

    private BankAccount bankAccount;

    public AddBankAccountResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddBankAccountResponse(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}