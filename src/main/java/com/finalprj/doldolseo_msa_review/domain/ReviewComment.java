package com.finalprj.doldolseo_msa_review.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "REVIEW_BOARD_COMMENT_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="REVIEW_BOARD_COMMENT_SEQ_GEN",
        sequenceName="REVIEW_BOARD_COMMENT_SEQ",
        allocationSize=1)
@Setter
@ToString
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_BOARD_COMMENT_SEQ_GEN")
    @Column(name = "COMMENT_NO")
    private Long commentNo;

    @ManyToOne
    @JoinColumn(name = "REVIEW_NO")
    private Review review;

    private String memberiD;

    private String content;

    @Column(name = "w_date")
    private LocalDateTime wDate;
}
