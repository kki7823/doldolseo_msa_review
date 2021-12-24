package com.finalprj.doldolseo_msa_review.service;

import com.finalprj.doldolseo_msa_review.domain.Review;
import com.finalprj.doldolseo_msa_review.domain.ReviewComment;
import com.finalprj.doldolseo_msa_review.dto.ReviewCommentDTO;
import com.finalprj.doldolseo_msa_review.repository.ReviewCommentRepository;
import com.finalprj.doldolseo_msa_review.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {
    @Autowired
    ReviewCommentRepository reviewCommentRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ReviewCommentDTO> getComments(Long reviewNo) {
        List<ReviewComment> commentEntity = reviewCommentRepository.findAllByReview_ReviewNo(reviewNo);
        return entityListToDtoList(commentEntity);
    }

    @Override
    public ReviewCommentDTO insertComment(ReviewCommentDTO dto) {
        setWDate(dto);
        setReview(dto);
        ReviewComment comment = reviewCommentRepository.save(dtoToEntity(dto));
        return entityToDto(comment);
    }

    public void setWDate(ReviewCommentDTO dto) {
        dto.setWDate(LocalDateTime.now());
    }

    public void setReview(ReviewCommentDTO dto) {
        Review review = reviewRepository.findByReviewNo(dto.getReview().getReviewNo());
        dto.setReview(review);
    }

    @Override
    @Transactional
    public void updateComment(Long commentNo, ReviewCommentDTO dto) {
        ReviewComment comment = reviewCommentRepository.findByCommentNo(commentNo);
        comment.setWDate(LocalDateTime.now());
        comment.setContent(dto.getContent());
    }

    @Override
    public void deleteComment(Long commentNo) {
        reviewCommentRepository.deleteById(commentNo);
    }

    public ReviewComment dtoToEntity(ReviewCommentDTO dto) {
        return modelMapper.map(dto, ReviewComment.class);
    }

    public ReviewCommentDTO entityToDto(ReviewComment comment) {
        return modelMapper.map(comment, ReviewCommentDTO.class);
    }

    public List<ReviewCommentDTO> entityListToDtoList(List<ReviewComment> commentList) {
        return modelMapper.map(commentList, new TypeToken<List<ReviewCommentDTO>>() {
        }.getType());
    }

}
