package com.finalprj.doldolseo_msa_review.repository;

import com.finalprj.doldolseo_msa_review.domain.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
    List<ReviewComment> findAllByReview_ReviewNo(Long reviewNo);
    ReviewComment findByCommentNo(Long commnetNo);
    long countReviewCommentsByReview_ReviewNo(Long reviewNo);
}
