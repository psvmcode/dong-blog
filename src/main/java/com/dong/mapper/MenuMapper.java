package com.dong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> getMenuByUserId(Object loginId);

}
