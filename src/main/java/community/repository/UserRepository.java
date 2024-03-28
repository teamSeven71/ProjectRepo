package community.repository;

import  community.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);  // username으로 사용자 정보를 가져옴

    @Override
    <S extends UserEntity> S save(S entity);
}