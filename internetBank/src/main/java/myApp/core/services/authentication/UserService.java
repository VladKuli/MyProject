package myApp.core.services.authentication;

import myApp.core.database.BankRepository;
import myApp.core.database.UserRepository;
import myApp.core.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
@Setter
@Getter
//@Componentomponent
//@Transactionalnsactional
public class UserService {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private UserRepository userRepository;

    private String personalCode;

    private Long id;

    public boolean logIn(String login, String password) {
        Optional<User> user = userRepository.logIn(login,password);
        if (user.isPresent()) {
            setId(user.get().getId());
            return true;
        }
        return false;
    }

    public void logOut() {
        if (this.personalCode != null && this.id != null) {
            this.personalCode = null;
            this.id = null;
        }
    }
}
