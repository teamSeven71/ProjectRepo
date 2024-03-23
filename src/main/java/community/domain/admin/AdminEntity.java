package community.domain.admin;

import community.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity {

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;


    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;
}
