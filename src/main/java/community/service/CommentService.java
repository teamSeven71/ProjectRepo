package community.service;

import community.domain.user.ArticleEntity;
import community.domain.user.CommentEntity;
import community.domain.user.UserEntity;
import community.dto.user.CommentDto;
import community.mapper.user.ArticleMapper;
import community.mapper.user.CommentMapper;
import community.repository.ArticleRepository;
import community.repository.CommentRepository;
import community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper,
                          UserRepository userRepository, ArticleRepository articleRepository ) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.articleRepository = articleRepository;
    }


    //해당 게시글의 댓글 작성
    public CommentDto.CommentResponseDto createComment(Long userId, CommentDto.CommentRequestDto commentRequestDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

        ArticleEntity articleEntity = articleRepository.findById(commentRequestDto.getArticleId())
                .orElseThrow(() -> new NoSuchElementException("Article not found"));


        LocalDateTime currentTime = LocalDateTime.now();
        CommentEntity savedComment = commentRepository.save(commentMapper.toRequestEntity(commentRequestDto, userEntity, articleEntity));

        savedComment.setCreateAt(currentTime);
        savedComment = commentRepository.save(savedComment); //작성 시간 설정

        CommentDto.CommentResponseDto responseDto = commentMapper.toResponseDto(savedComment);
        responseDto.setUserId(userId);
        responseDto.setArticleId(commentRequestDto.getArticleId());


        return responseDto;
    }

    //댓글 get은 해당 Article 조회시 가져옴 -> ArticleService에서 처리


    //댓글 수정


    //댓글 삭제

}
