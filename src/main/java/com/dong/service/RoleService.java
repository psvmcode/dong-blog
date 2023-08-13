package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.common.ResponseResult;
import com.dong.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {


    ResponseResult listRole(String name);

    ResponseResult insertRole(Role role);

    ResponseResult updateRole(Role role);

    ResponseResult deleteBatch(List<Integer> ids);

    ResponseResult getCurrentUserRole();

    ResponseResult selectById(Integer roleId);

}
