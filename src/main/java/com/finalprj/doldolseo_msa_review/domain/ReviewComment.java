package com.finalprj.doldolseo_msa_review.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "REVIEW_COMMENT_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@ToString
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_NO")
    private Long commentNo;

    @ManyToOne
    @JoinColumn(name = "REVIEW_NO")
    private Review review;

    private String id;
    private String content;

    @Column(name = "W_DATE")
    private LocalDateTime wDate;
}
