package com.dong.mapper;

import com.dong.entity.JobLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface JobLogMapper extends BaseMapper<JobLog> {

    void clean();

}
