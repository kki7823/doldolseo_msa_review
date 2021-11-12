package com.finalprj.doldolseo_msa_review;

import com.finalprj.doldolseo_msa_review.DoldolseoMsaReviewApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DoldolseoMsaReviewApplication.class);
    }

}
