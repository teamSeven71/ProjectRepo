package community.interceptor;

import community.constant.Role;
import community.domain.user.CommentEntity;
import community.repository.CommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.util.Collection;

@Component
public class CommentAuthInterceptor implements HandlerInterceptor {
    //아래서 articleRepository 사용 하려고 선언
    private final CommentRepository commentRepository;

    @Autowired
    public CommentAuthInterceptor(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {

            CommentEntity comment = fetchComment(request);

            if (comment != null){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                    // 현재 로그인한 사용자의 정보
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    // 사용자의 ID 등 필요한 정보를 이용하여 게시물의 작성자와 비교하여 권한 확인
                    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                    for (GrantedAuthority authority : authorities) {
                        if (authority.getAuthority().equals(Role.ADMIN.name())) {
                            // 사용자가 ADMIN 권한을 가지고 있으면 true 반환
                            return true;
                        }
                    }
                    if (!comment.getUser().getUsername().equals(userDetails.getUsername())) {
                        // 권한이 없는 경우
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // HTTP 응답 코드를 403으로 설정
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println("<script>alert('해당 글의 작성자만 수정및 삭제할 수 있습니다.');history.go(-1);</script>");
                        out.flush();
                        return false; // 요청 처리 중지
                    }
                }
            }
        }
        return true;
    }



    private CommentEntity fetchComment(HttpServletRequest request) {
        String idParameter = request.getParameter("commentId");
        if (idParameter != null) {
            try {
                Long commentId = Long.parseLong(idParameter);
                return commentRepository.findById(commentId).orElse(null);
            } catch (NumberFormatException e) {
                // 파라미터를 Long으로 파싱할 수 없는 경우
                return null;
            }
        }
        return null;
    }

}
