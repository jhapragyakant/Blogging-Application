package com.pragyakantjha.blogging.controllers;


import com.pragyakantjha.blogging.payload.CommentDto;
import com.pragyakantjha.blogging.services.CommentService;
import com.pragyakantjha.blogging.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable Integer postId ){
        CommentDto commentDto1 = commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse(true, "Comment deleted Successfully!"), HttpStatus.OK);
    }
}
