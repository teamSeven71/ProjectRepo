package community.service;

import community.constant.Role;
import community.domain.user.UserEntity;
import community.dto.user.AddUserRequest;
import community.dto.user.UserDto;
import community.mapper.user.UserMapper;
import community.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }


    public UserEntity save(AddUserRequest dto) {
        return userRepository.save(
                UserEntity.builder()
                        .email(dto.getUsername())
                        .password(encoder.encode(dto.getPassword()))// 패스워드 암호화
                        .name(dto.getName())
                        .nickName(dto.getNickname())
                        .role(Role.USER)
                        .build()
        );
    }

    public Page<UserDto.UserResponseDto> getAllActiveMembers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponseDto);
    }

    public void deleteUsers(List<Long> userIds){
        userRepository.deleteAllById(userIds);
    }

    public boolean existsByEmail(String email){
        boolean result = userRepository.existsByEmail(email);
        return result;
    }

    @Transactional
    public UserDto.UserResponseDto updateUser(Long userId, String nickName){

        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with id: " +userId);
        }

        UserEntity user = optionalUser.get();

        user.setNickName(nickName);

        userRepository.save(user);

        return userMapper.toResponseDto(user);
    }

//    public boolean existsByUsername(String email) {
//        boolean result = userRepository.existsByUsername(email);
//    }


    public boolean existsByNickName(String nickname){
        return userRepository.existsByNickName(nickname);
    }

}
