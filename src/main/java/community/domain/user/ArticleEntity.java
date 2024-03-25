package community.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import community.common.TimeStamp;
import community.constant.CategoryType;
import community.constant.Role;
import jakarta.persistence.*;
import lombok.*;

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

    private String title; //제목

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; //내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Enumerated(EnumType.STRING)
    private CategoryType type = CategoryType.ARTICLE;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();

}
