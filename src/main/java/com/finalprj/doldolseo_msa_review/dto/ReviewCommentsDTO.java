package com.finalprj.doldolseo_msa_review.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewCommentsDTO {
    private List<ReviewCommentDTO> comments;
    private Long numOfComments;
}
