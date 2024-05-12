package myApp.core.services.authentication;

import myApp.core.database.BankAccountRepository;
import myApp.core.database.BankRepository;
import myApp.core.database.UserRepository;
import myApp.core.domain.BankAccount;
import myApp.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Component
@Transactional
public class UserAreAdminService {

    @Autowired
    private BankAccountRepository bankRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean isUserAreAdmin(String personalCode) {
        BankAccount bankAccounts = bankRepository.getByPersonalCode(personalCode);
        /*
        Optional<BankAccount> bankAccount = bankAccounts.stream()
                .filter(bankAccount1 -> bankAccount1.getPersonalCode().equals(personalCode))
                .findFirst();

         */
        Optional<User> user = userRepository.getAllUsers().stream()
                .filter(user1 -> user1.getPersonalCode().equals(personalCode))
                .findFirst();

           // return  bankAccount.get().getPersonalCode().equals(user.get().getPersonalCode()) || user.get().getRole().equals("Role_Admin");
        return user.get().getRole().equals("Role_Admin");
    }
}