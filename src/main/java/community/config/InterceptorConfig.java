package community.config;

import community.interceptor.AuthInterceptor;
import community.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //AuthInterceptor 생성자에서 articleRepository 사용하는데 넣어주려고 생성
    private final ArticleRepository articleRepository;

    @Autowired
    public InterceptorConfig(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(articleRepository))
                //실행 순서
                .order(1)
                //지정 url 일때만 해당 Interceptor 실행
                .addPathPatterns("/article/post", "/api/articles/delete/**");
    }
}
