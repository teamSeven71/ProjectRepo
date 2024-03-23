package community.domain.admin;

import community.common.TimeStamp;
import community.constant.CategoryType;
import community.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity extends TimeStamp {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //제목

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; //내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private UserEntity admin;


    @Enumerated(EnumType.STRING)
    private CategoryType type = CategoryType.NOTICE;
}
