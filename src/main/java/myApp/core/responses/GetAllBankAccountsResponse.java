package myApp.core.responses;


import lombok.Getter;
import myApp.core.domain.BankAccount;

import java.util.List;


@Getter
public class GetAllBankAccountsResponse extends CoreResponse {

    private List<BankAccount> bankAccounts;

    public GetAllBankAccountsResponse(List<CoreError> errors, List<BankAccount> bankAccounts) {
        super(errors);
        this.bankAccounts = bankAccounts;
    }
}