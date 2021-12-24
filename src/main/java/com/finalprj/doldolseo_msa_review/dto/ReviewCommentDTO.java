package com.finalprj.doldolseo_msa_review.dto;

import com.finalprj.doldolseo_msa_review.domain.Review;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewCommentDTO {
    private Long commentNo;
    private Review review;
    private String id;
    private String content;
    private LocalDateTime wDate;
}
