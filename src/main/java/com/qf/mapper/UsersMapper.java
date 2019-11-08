package com.qf.mapper;

import java.util.Map;
import java.util.Set;

public interface UsersMapper {

    public String getPasswordByUsername(String username);

    //根据用户名获取角色列表
    public Set<String> getRolesByUsername(String username);

    //根据角色获取权限列表
    public Set<String> getPermissionsByRoleName(Map<String,Set> map);
}
