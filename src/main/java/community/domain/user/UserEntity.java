package community.domain.user;

import community.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; //사용자 아이디

    private String password;

    private String name; //사용자 이름

    private String nickName;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

}
