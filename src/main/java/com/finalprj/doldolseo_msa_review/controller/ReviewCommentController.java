package com.finalprj.doldolseo_msa_review.controller;

import com.finalprj.doldolseo_msa_review.dto.ReviewCommentDTO;
import com.finalprj.doldolseo_msa_review.service.ReviewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewCommentController {
    @Autowired
    ReviewCommentService service;

    @GetMapping(value = "/review/{reviewNo}/comment")
    public ResponseEntity<List<ReviewCommentDTO>> getReviewComment(@PathVariable("reviewNo") Long reviewNo) {
        return ResponseEntity.ok(service.getComments(reviewNo));
    }

    @PostMapping("/review/{reviewNo}/comment")
    public ResponseEntity<String> insertReviewComment(@PathVariable("reviewNo") Long reviewNo,
                                                      @RequestBody ReviewCommentDTO dto) {
        service.insertComment(dto);
        return ResponseEntity.ok("댓글 삽입 완료");
    }

    @DeleteMapping("/review/{reviewNo}/comment/{commentNo}")
    public void deleteReviewComment(@PathVariable("reviewNo") Long reviewNo,
                                    @PathVariable("commentNo") Long commentNo) {
        service.deleteComment(commentNo);
        System.out.println(commentNo + "댓글 삭제 완료");
    }

    @PutMapping("/review/{reviewNo}/comment/{commentNo}")
    public void putReviewComment(@PathVariable("reviewNo") Long reviewNo,
                                 @PathVariable("commentNo") Long commentNo,
                                 @RequestBody ReviewCommentDTO dto) {

        service.updateComment(commentNo, dto);
        System.out.println(commentNo + "번 댓글 수정 완료");
    }
}
