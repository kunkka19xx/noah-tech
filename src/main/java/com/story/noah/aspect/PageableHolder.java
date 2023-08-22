package com.story.noah.aspect;

import org.springframework.data.domain.Pageable;

public class PageableHolder {
    private static ThreadLocal<Pageable> pageableThreadLocal = new ThreadLocal<>();

    public static Pageable getPageable() {
        return pageableThreadLocal.get();
    }

    public static void setPageable(Pageable pageable) {
        pageableThreadLocal.set(pageable);
    }

    public static void clear() {
        pageableThreadLocal.remove();
    }
}
