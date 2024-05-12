package myApp.core.requests;

import lombok.Setter;
import myApp.core.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class GetAllUsersRequest {

    List<User> users;
}
