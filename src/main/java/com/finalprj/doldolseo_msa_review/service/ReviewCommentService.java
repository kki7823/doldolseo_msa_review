package com.finalprj.doldolseo_msa_review.service;

import com.finalprj.doldolseo_msa_review.dto.ReviewCommentDTO;

import java.util.List;

public interface ReviewCommentService {
    List<ReviewCommentDTO> getComments(Long reviewNo);

    ReviewCommentDTO insertComment(ReviewCommentDTO dto);

    void updateComment(Long commentNo, ReviewCommentDTO dto);

    void deleteComment(Long commentNo);
}
