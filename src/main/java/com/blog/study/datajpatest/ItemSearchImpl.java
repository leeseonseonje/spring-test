package com.blog.study.datajpatest;

import org.springframework.stereotype.Component;

@Component
public class ItemSearchImpl implements ItemSearch {

    @Override
    public boolean search(TYPE type, String title) {
        return true;
    }
}
