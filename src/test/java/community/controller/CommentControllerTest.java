package community.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import community.domain.user.ArticleEntity;
import community.domain.user.CommentEntity;
import community.domain.user.UserEntity;
import community.dto.user.CommentDto;
import community.repository.ArticleRepository;
import community.repository.CommentRepository;
import community.repository.UserRepository;
import community.service.CommentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;


    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp() {
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("댓글 조회에 성공한다")
    public void readCommentTest() throws Exception {
        // Given
        Long articleId = 1L;
        CommentDto.CommentResponseDto commentResponseDto = new CommentDto.CommentResponseDto();
        List<CommentDto.CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        commentResponseDtoList.add(commentResponseDto);

        // CommentService의 모의 객체(Mock) 생성
        CommentService commentServiceMock = Mockito.mock(CommentService.class);

        // When
        when(commentServiceMock.readComment(anyLong())).thenReturn(commentResponseDtoList);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/comments/read")
                        .param("articleId", articleId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("댓글 생성에 성공한다")
    public void createCommentTest() throws Exception {
        // Given
        Long userId = 1L;
        Long articleId = 1L;
        CommentDto.CommentRequestDto commentRequestDto = new CommentDto.CommentRequestDto();
        commentRequestDto.setContent("댓글 내용");

        UserEntity user = new UserEntity();
        user.setId(userId);

        // Mocking the behavior of userRepository.findById method
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // Mocking the behavior of articleRepository.findById method
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(new ArticleEntity()));

        // Mocking the behavior of commentService.createComment method
        CommentDto.CommentResponseDto responseDto = new CommentDto.CommentResponseDto();
        responseDto.setId(1L); // Example ID
        when(commentService.createComment(any(Long.class), any(CommentDto.CommentRequestDto.class)))
                .thenReturn(responseDto);

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/comments/create")
                        .param("articleId", articleId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commentRequestDto)))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @DisplayName("댓글 수정에 성공한다")
    public void updateCommentTest() throws Exception {
        // Given
        Long commentId = 1L;

        // 댓글을 데이터베이스에 추가
        CommentEntity comment = new CommentEntity();
        comment.setId(commentId);
        // 댓글 저장
        commentRepository.save(comment);

        CommentDto.CommentRequestDto commentRequestDto = new CommentDto.CommentRequestDto();
        commentRequestDto.setContent("수정된 댓글 내용");

        // When
        // 댓글이 존재할 경우에만 수정을 시도하도록 수정
        when(commentRepository.existsById(commentId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/comments/update/{commentId}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commentRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("댓글 삭제에 성공한다")
    public void deleteCommentTest() throws Exception {
        // Given
        Long commentId = 1L;

        // 댓글을 데이터베이스에 추가
        CommentEntity comment = new CommentEntity();
        comment.setId(commentId);
        // 다른 필요한 필드들을 설정

        // 댓글 저장
        commentRepository.save(comment);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/comments/delete")
                        .param("commentId", commentId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
