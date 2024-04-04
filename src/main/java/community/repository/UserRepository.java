package community.repository;

import  community.domain.user.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);  // email로 사용자 정보를 가져옴

    @Override
    <S extends UserEntity> S save(S entity);

  /*  @Query("select a from UserEntity a ORDER BY a.id ASC")
//    @Query("select a from UserEntity a where a.active = :type ORDER BY a.id ASC")
    List<UserEntity> findAllActiveMembers();
*/
  // 사용자 활성화 여부에 따라 사용자 목록을 가져오는 메소드
    Page<UserEntity> findAll(Pageable pageable);

    @Override
    List<UserEntity> findAllById(Iterable<Long> longs);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    <S extends UserEntity> boolean exists(Example<S> example);

    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);
}