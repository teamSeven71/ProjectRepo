package community.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import community.common.TimeStamp;
import community.constant.CategoryType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity extends TimeStamp {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; //내용

    private Long likeNum; //좋아요개수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private ArticleEntity article;


}
