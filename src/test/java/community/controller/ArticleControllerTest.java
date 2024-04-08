package community.controller;

import community.constant.Role;
import community.domain.user.UserEntity;
import community.dto.user.ArticleDto;
import community.mapper.user.ArticleMapper;
import community.repository.ArticleRepository;
import community.repository.UserRepository;
import community.service.ArticleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
        articleRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 등록 성공")
    void addArticle() throws Exception {
        // given
        UserEntity user = new UserEntity();
        user.setEmail("user@naver.com");
        user.setName("user");
        user.setNickName("user_nickName");
        user.setPassword(bCryptPasswordEncoder.encode("user"));
        user.setRole(Role.USER);
        ArticleDto.ArticleRequestDto request = ArticleDto.ArticleRequestDto.builder().title("테스트 게시글 제목").content("테스트 게시글 내용").build();

        // when
        ArticleDto.ArticleResponseDto responseDto = articleService.save(request, user);

        // then
        assertThat(responseDto.getContent()).isEqualTo(request.getContent());
        assertThat(responseDto.getTitle()).isEqualTo(request.getTitle());
    }

    @DisplayName("게시물 단 건 조회")
    @Test
    void getArticle() {
        //given
        ArticleDto.ArticleResponseDto article1;
        Mockito.when(articleService.find)
        //when

        //then
    }

    @DisplayName("게시글 단 건 삭제")
    @Test
    void deleteArticleById() {
        //given

        //when

        //then
    }

    @DisplayName("게시물 단 건 수정")
    @Test
    void updateBoard() {
        //given

        //when

        //then
    }
}