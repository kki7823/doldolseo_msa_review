package com.finalprj.doldolseo_msa_review.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * 후기게시판 Entity
 *
 * @Author 김경일
 * @Date 2021/08/06
 */

@Entity
@Table(name = "REVIEW_BOARD_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_NO")
    private Long reviewNo;

    @Column(name = "ID")
    private String memberId;

    private String title;
    private String content;

    @Column(name = "COURSE_IMG")
    private String courseImgName;

    @Column(name = "UPLOAD_IMG")
    private String uploadImgNames;

    @Column(name = "W_DATE")
    private LocalDateTime wDate;

    private int hit;

    @Column(name = "AREA_NO")
    private int areaNo;
}
