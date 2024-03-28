package community.service;

import community.constant.Role;
import community.domain.user.UserEntity;
import community.dto.user.AddUserRequest;
import community.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(username));
    }

    public UserEntity save(AddUserRequest dto) {
        return userRepository.save(
                UserEntity.builder()
                        .username(dto.getUsername())
                        .password(encoder.encode(dto.getPassword()))// 패스워드 암호화
                        .name(dto.getName())
                        .nickName(dto.getNickname())
                        .role(Role.USER)
                        .build()
        );
    }
}
