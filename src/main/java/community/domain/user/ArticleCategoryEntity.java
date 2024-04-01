package community.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.awt.print.Book;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategoryEntity {

    @Id
    @Column(name = "articleCategory_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

}
