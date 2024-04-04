package community;

import community.constant.Role;
import community.domain.user.ArticleCategoryEntity;
import community.domain.user.ArticleEntity;
import community.domain.user.CategoryEntity;
import community.domain.user.UserEntity;
import community.repository.CategoryRepository;
import community.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Init {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void initFirst(){
        initUsers();
        initCategory();
    }

    @Transactional
    public void initUsers() {

        UserEntity admin = new UserEntity();
        admin.setEmail("admin@naver.com");
        admin.setNickName("admin");
        admin.setName("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        for (int i = 0; i < 5; i++) {
            UserEntity user = new UserEntity();
            user.setEmail("user" + (i+1) + "@naver.com");
//            user.setUsername("user" + (i+1));
            user.setNickName("userNickname" + (i+1));
            user.setName("user" + (i+1));
            user.setPassword(bCryptPasswordEncoder.encode("user" + (i+1)));
            user.setRole(Role.USER);
            userRepository.save(user);
        }
    }

    @Transactional
    public void initCategory() {
        String[] categories = {"ARTICLE", "NOTICE", "JAVA", "SQL", "JS", "ETC", "PYTHON", "DB"};

        for (String categoryName : categories) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setCategoryName(categoryName);
            categoryRepository.save(categoryEntity);
        }
    }
}

