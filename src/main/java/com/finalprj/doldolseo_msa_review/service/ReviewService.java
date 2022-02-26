package com.finalprj.doldolseo_msa_review.service;

import com.finalprj.doldolseo_msa_review.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * 후기게시판 Service
 *
 * @Author 김경일
 * @Date 2021/08/06
 */

public interface ReviewService {
    Page<ReviewDTO> getReviewPage(Integer areaNo, Pageable pageable);

    ReviewDTO getReview(Long reviewNo);

    ReviewDTO insertReview(ReviewDTO dto);

    boolean updateReview(Long reviewNo, ReviewDTO dto, String idTryToUpdate);

    boolean deleteReview(Long reviewNo, String idTryDelete);
}
