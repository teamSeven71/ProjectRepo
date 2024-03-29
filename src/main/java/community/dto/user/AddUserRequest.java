package community.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String username;
    private String password;
    private String nickname;
    private String name;
}