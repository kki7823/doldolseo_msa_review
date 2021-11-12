package com.finalprj.doldolseo_msa_review.service.impl;

import com.finalprj.doldolseo_msa_review.domain.Review;
import com.finalprj.doldolseo_msa_review.dto.ReviewDTO;
import com.finalprj.doldolseo_msa_review.repository.ReviewRepository;
import com.finalprj.doldolseo_msa_review.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository repository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<ReviewDTO> getReviewPage(Integer areaNo, Pageable pageable) {
        return (areaNo == null) ? getReviewPage(pageable) : getReviewPageByArea(areaNo, pageable);
    }

    public Page<ReviewDTO> getReviewPage(Pageable pageable) {
        Page<Review> reviewPage = repository.findAll(pageable);
        return entityPageToDtoPage(reviewPage);
    }

    public Page<ReviewDTO> getReviewPageByArea(Integer areaNo, Pageable pageable) {
        Page<Review> reviewPage = repository.findAllByAreaNo(areaNo, pageable);
        return entityPageToDtoPage(reviewPage);
    }

    @Override
    public ReviewDTO getReview(Long reviewNo) {
        Review review = repository.findByReviewNo(reviewNo);
        return entityToDto(review);
    }

    public ReviewDTO getReviewHitAndChangeContentImgSource(Long reviewNo) {
        Review review = repository.findByReviewNo(reviewNo);
        increaseHit(review);
        changeContentImgSrc(review);
        return entityToDto(review);
    }

    @Transactional
    public void changeContentImgSrc(Review review) {
        String content = review.getContent();
        if (content != null) {
            review.setContent(content.replace("temp", "" + review.getReviewNo()));
        }
    }

    @Transactional
    public void increaseHit(Review review) {
        review.setHit(review.getHit() + 1);
    }

    public void getDTOfilledValues(ReviewDTO dto) {
        setUploadImgName(dto);
        setCourseImgName(dto);
        dto.setHit(1);
        dto.setWDate(LocalDateTime.now());
    }

    public void setUploadImgName(ReviewDTO dto) {
        if (dto.getUploadImgs() != null) {
            String uploadImg = Arrays.stream(dto.getUploadImgs()).map(s -> s = s.split("temp")[1].substring(1)).collect(Collectors.joining(","));
            dto.setUploadImgNames(uploadImg);
        }
    }

    public void setCourseImgName(ReviewDTO dto) {
        if (dto.getCourseImgFile() != null) {
            dto.setCourseImgName(dto.getCourseImgFile().getOriginalFilename());
        }
    }


    @Override
    public ReviewDTO insertReview(ReviewDTO dto) {
        Review review = repository.save(dtoToEntity(dto));
        return entityToDto(review);
    }

    @Override
    public void deleteReview(Long reviewNo) {
        repository.deleteById(reviewNo);
        System.out.println(reviewNo + "번 게시글 삭제");
    }

    public void updateUploadImgName(ReviewDTO dto) {
        if (dto.getUploadImgs() != null) {
            String uploadImgName = dto.getUploadImgNames() + "," + Arrays.stream(dto.getUploadImgs()).map(s -> s = s.split("temp")[1].substring(1)).collect(Collectors.joining(","));
            dto.setUploadImgNames(uploadImgName);
        }
    }

    @Override
    @Transactional
    public void updateReview(Long reviewNo, ReviewDTO dto) {
        Review review = repository.findByReviewNo(reviewNo);
        review.setTitle(dto.getTitle());
        review.setContent(dto.getContent());
        review.setUploadImgNames(dto.getUploadImgNames());
        review.setAreaNo(dto.getAreaNo());
    }


    public Review dtoToEntity(ReviewDTO dto) {
        return modelMapper.map(dto, Review.class);
    }

    public ReviewDTO entityToDto(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }

    public Page<ReviewDTO> entityPageToDtoPage(Page<Review> reviewPage) {
        return modelMapper.map(reviewPage, new TypeToken<Page<ReviewDTO>>() {
        }.getType());
    }
}
