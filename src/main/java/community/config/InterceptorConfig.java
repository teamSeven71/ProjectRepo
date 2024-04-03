package community.config;

import community.interceptor.ArticleAuthInterceptor;
import community.interceptor.CommentAuthInterceptor;
import community.repository.ArticleRepository;
import community.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //AuthInterceptor 생성자에서 articleRepository 사용하는데 넣어주려고 생성
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public InterceptorConfig(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ArticleAuthInterceptor(articleRepository))
                //실행 순서
                .order(1)
                //지정 url 일때만 해당 Interceptor 실행
                .addPathPatterns("/article/post", "/api/articles/delete/**");

        registry.addInterceptor(new CommentAuthInterceptor(commentRepository))
                //실행 순서
                .order(2)
                //지정 url 일때만 해당 Interceptor 실행
                .addPathPatterns("/api/comments/update", "/api/comments/delete");
    }
}
