package myApp.core.responses;

import lombok.Getter;
import myApp.core.domain.User;

import java.util.List;
@Getter
public class GetAllUsersResponse extends CoreResponse {

    private List<User> users;

    public GetAllUsersResponse(List<CoreError> errors, List<User> users) {
        super(errors);
        this.users = users;
    }
}