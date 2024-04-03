package community.repository;

import  community.domain.user.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);  // username으로 사용자 정보를 가져옴

    @Override
    <S extends UserEntity> S save(S entity);

    @Query("select a from UserEntity a ORDER BY a.id ASC")
//    @Query("select a from UserEntity a where a.active = :type ORDER BY a.id ASC")
    List<UserEntity> findAllActiveMembers();

    @Override
    List<UserEntity> findAllById(Iterable<Long> longs);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    <S extends UserEntity> boolean exists(Example<S> example);

    boolean existsByUsername(String userName);
    boolean existsByNickName(String nickName);
}