package com.finalprj.doldolseo_msa_review.service;

import com.finalprj.doldolseo_msa_review.dto.ReviewCommentDTO;
import com.finalprj.doldolseo_msa_review.dto.ReviewCommentsDTO;

import java.util.List;

public interface ReviewCommentService {
    ReviewCommentsDTO getComments(Long reviewNo);

    ReviewCommentDTO insertComment(ReviewCommentDTO dto);

    boolean updateComment(Long commentNo, ReviewCommentDTO dto, String idTryToUpdate);

    boolean deleteComment(Long commentNo, String idTryToDelete);
}
