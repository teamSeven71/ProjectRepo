package community.controller;

import community.dto.user.CommentDto;
import community.service.ArticleService;
import community.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 CRUD")
@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDto.CommentResponseDto> createComment(@RequestParam("userId") Long userId, @RequestBody CommentDto.CommentRequestDto commentRequestDto) {
        CommentDto.CommentResponseDto responseDto = commentService.createComment(userId, commentRequestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().body("Deleted Comment Id : " + commentId);
    }


}
