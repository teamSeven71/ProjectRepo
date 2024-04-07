package community.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import community.constant.Role;
import community.domain.user.UserEntity;
import community.dto.user.CheckDuplicateRequest;
import community.dto.user.DeleteUserIdsRequest;
import community.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @DisplayName("사용자 등록 성공")
    @Test
    void signup() throws Exception {
        // Given
        UserEntity user = new UserEntity();
        user.setEmail("user@naver.com");
        user.setName("user");
        user.setNickName("user_nickName");
        user.setPassword(bCryptPasswordEncoder.encode("user"));
        user.setRole(Role.USER);

        // When
        UserEntity user1 = userRepository.save(user);

        //Then
        assertThat(user1.getName()).isEqualTo(user.getName());
        assertThat(user1.getEmail()).isEqualTo(user.getEmail());
        assertThat(user1.getNickName()).isEqualTo(user.getNickName());
        assertThat(user1.getPassword()).isEqualTo(user.getPassword());
        assertThat(user1.getRole()).isEqualTo(user.getRole());
    }

    @DisplayName("사용자 삭제 성공")
    @Test
    void deleteUsers() throws Exception {

        // Given
        Long userIds = 1L;
        DeleteUserIdsRequest request = new DeleteUserIdsRequest(Collections.singletonList(userIds));

        // When & Then
        mockMvc.perform(delete("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("이메일 중복 확인 성공")
    @Test
    void checkEmail() throws Exception {
        // Given
        CheckDuplicateRequest request = new CheckDuplicateRequest();
        request.setEmail("user222@naver.com");

        // When
        mockMvc.perform(post("/checkEmail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))

                // Then
                .andExpect(status().isOk());
    }

    @DisplayName("닉네임 중복 확인 성공")
    @Test
    void checkNickName() throws Exception {
        // Given
        CheckDuplicateRequest request = new CheckDuplicateRequest();
        request.setNickName("testNickname");

        // When
        mockMvc.perform(post("/checkNickName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))

                // Then
                .andExpect(status().isOk());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
