package community.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import community.common.TimeStamp;
import community.constant.CategoryType;
import community.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity extends TimeStamp {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false)
    private String title; //제목

    @Lob
    @Column(name="content", nullable=false, columnDefinition = "TEXT")
    private String content; //내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CommentEntity> comments = new ArrayList<>();

    @CreatedDate
    private LocalDateTime deletedAt;

    private Long goodCount;

    private Long badCount;

    private Long viewCount;

    @OneToMany(mappedBy = "article")
    private List<ArticleCategoryEntity> articleCategories = new ArrayList<>();

}
