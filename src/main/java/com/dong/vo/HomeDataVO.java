package com.dong.vo;

import com.dong.entity.BlogArticle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeDataVO {

    private Map<String, Object> contribute;

    private Map<String, Object> categoryList;

    private List<Map<String,Object>> userAccess;

    private List<Map<String,Object>> tagsList;

    private String dashboard;

    private List<BlogArticle> blogArticles;

}
