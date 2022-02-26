package com.finalprj.doldolseo_msa_review.service;

import com.finalprj.doldolseo_msa_review.domain.Review;
import com.finalprj.doldolseo_msa_review.domain.ReviewComment;
import com.finalprj.doldolseo_msa_review.dto.ReviewCommentDTO;
import com.finalprj.doldolseo_msa_review.dto.ReviewCommentsDTO;
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
    public ReviewCommentsDTO getComments(Long reviewNo) {
        ReviewCommentsDTO commentsDTO = new ReviewCommentsDTO();

        List<ReviewComment> commentEntity = reviewCommentRepository.findAllByReview_ReviewNo(reviewNo);
        commentsDTO.setComments(entityListToDtoList(commentEntity));

        Long numOfCommnets = reviewCommentRepository.countReviewCommentsByReview_ReviewNo(reviewNo);
        commentsDTO.setNumOfComments(numOfCommnets);

        return commentsDTO;
    }

    @Override
    @Transactional
    public boolean updateComment(Long commentNo, ReviewCommentDTO dto, String idTryToUpdate) {
        String writerId = reviewCommentRepository.findByCommentNo(commentNo).getId();

        if (!writerId.equals(idTryToUpdate)) {
            System.out.println("[Update Fail] Has no Authority ");
            return false;
        }

        ReviewComment comment = reviewCommentRepository.findByCommentNo(commentNo);
        comment.setWDate(LocalDateTime.now());
        comment.setContent(dto.getContent());
        return true;
    }

    @Override
    public boolean deleteComment(Long commentNo, String idTryToDelete) {
        String writerId = reviewCommentRepository.findByCommentNo(commentNo).getId();

        if (!writerId.equals(idTryToDelete)) {
            System.out.println("[Delete Fail] Has no Authority ");
            return false;
        }

        reviewCommentRepository.deleteById(commentNo);
        return true;
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
