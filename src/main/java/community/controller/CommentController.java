package community.controller;

import community.domain.user.UserEntity;
import community.dto.user.ArticleDto;
import community.dto.user.CommentDto;
import community.service.ArticleService;
import community.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글 CRUD")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/read")
    public ResponseEntity<List<CommentDto.CommentResponseDto>> readComment(@RequestParam("articleId") Long articleId) {
        List<CommentDto.CommentResponseDto> comments = commentService.readComment(articleId);
        return ResponseEntity.ok().body(comments);
    }


    @PostMapping("/create")
    public ResponseEntity<CommentDto.CommentResponseDto> createComment(@AuthenticationPrincipal UserEntity user, @RequestBody CommentDto.CommentRequestDto commentRequestDto) {
        CommentDto.CommentResponseDto responseDto = commentService.createComment(user.getId(),commentRequestDto);
        return ResponseEntity.ok().body(responseDto);
    }


    @PatchMapping("/update")
    public ResponseEntity<?> updateComment(@RequestParam("commentId") Long commentId, @RequestBody CommentDto.CommentPatchDto commentPatchDto, @AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok().body(commentService.updateComment(commentId, commentPatchDto));
    }



    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestParam("commentId") Long commentId, @AuthenticationPrincipal UserEntity user) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().body("Deleted Comment Id : " + commentId);
    }


}
