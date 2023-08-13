package com.dong;

import com.dong.common.ResponseResult;
import com.dong.service.ArticleService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author lidong14@kuaishou.com
 * Created on 2023-08-11
 */
public class FirstTest extends BaseTest {

    @Resource
    private ArticleService articleService;

    @Test
    public void one() {
        ResponseResult articleById = articleService.getArticleById(113L);
        System.out.println(articleById);
    }

}
