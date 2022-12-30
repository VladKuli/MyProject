package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SwitchUserRequest {

    private String login;
    private String password;
}
