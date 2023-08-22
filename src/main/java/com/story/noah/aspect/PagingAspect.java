package com.story.noah.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PagingAspect {

    @Before("@annotation(paging)")
    public void beforePaging(Paging paging) {
        Pageable pageable = PageRequest.of(paging.page() - 1, paging.size());
        PageableHolder.setPageable(pageable);
    }
}
