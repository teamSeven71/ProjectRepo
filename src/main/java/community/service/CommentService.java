package community.service;

import community.domain.user.ArticleEntity;
import community.domain.user.CommentEntity;
import community.domain.user.UserEntity;
import community.dto.user.ArticleDto;
import community.dto.user.CommentDto;
import community.mapper.user.ArticleMapper;
import community.mapper.user.CommentMapper;
import community.repository.ArticleRepository;
import community.repository.CommentRepository;
import community.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Slf4j
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper,
                          UserRepository userRepository, ArticleRepository articleRepository ) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.articleRepository = articleRepository;
    }

    //
    public List<CommentDto.CommentResponseDto> readComment(Long articleId) {
        List<CommentEntity> comments = commentRepository.findByArticleId(articleId);
        return comments.stream()
                .map(commentMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // 해당 게시글의 댓글 작성
    public CommentDto.CommentResponseDto createComment(Long userId, CommentDto.CommentRequestDto commentRequestDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

        ArticleEntity articleEntity = articleRepository.findById(commentRequestDto.getArticleId())
                .orElseThrow(() -> new NoSuchElementException("Article not found"));

        // 댓글 생성 시간을 따로 설정할 필요 없음
        CommentEntity savedComment = commentRepository.save(commentMapper.toRequestEntity(commentRequestDto, userEntity, articleEntity));

        // 이미 작성 시간이 설정되어 있을 것임
        savedComment = commentRepository.save(savedComment);

        CommentDto.CommentResponseDto responseDto = commentMapper.toResponseDto(savedComment);
        responseDto.setUserId(userId);
        responseDto.setArticleId(commentRequestDto.getArticleId());

        return responseDto;
    }

    //댓글 수정
    public CommentDto.CommentResponseDto updateComment(Long commentId, CommentDto.CommentPatchDto commentPatchDto) {


        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment not found" + commentId));

        //댓글 수정
        commentEntity.setContent(commentPatchDto.getContent()); // 댓글 내용 업데이트
        LocalDateTime currentTime = LocalDateTime.now();
        commentEntity.setModifiedAt(currentTime); //수정시간 설정
        commentEntity = commentRepository.save(commentEntity);

        return commentMapper.toResponseDto(commentEntity);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with id: " + commentId));

        try {
            // 해당 댓글을 작성한 사용자를 찾습니다.
            UserEntity userEntity = commentEntity.getUser();

            // 사용자가 작성한 댓글 목록에서 해당 댓글을 제거합니다.
            if (userEntity != null) {
                userEntity.getComments().remove(commentEntity);
                userRepository.save(userEntity);
            }

            // 댓글을 삭제합니다.
            commentRepository.delete(commentEntity);
            log.info("삭제된 Comment: {}", commentId);
        } catch (Exception e) {
            // 댓글 삭제 중 예외가 발생한 경우 처리
            log.error("댓글 삭제 중 예외 발생: {}", e.getMessage());
            throw new RuntimeException("댓글 삭제 중 문제가 발생했습니다.", e);
        }
    }



    //댓글 개수 조회
    public Long countComment(Long articleId) {
        Long countcomment = commentRepository.countComment(articleId);

        return countcomment;
    }
}
