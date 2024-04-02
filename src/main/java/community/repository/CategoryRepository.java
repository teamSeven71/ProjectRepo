package community.repository;

import community.domain.user.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // 기본적으로 JpaRepository가 제공하는 메서드 외에 필요한 추가 메서드가 있다면 여기에 선언할 수 있습니다.
}
