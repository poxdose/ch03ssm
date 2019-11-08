package com.qf.service.impl;

import com.qf.mapper.UsersMapper;
import com.qf.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public String getPasswordByUsername(String username) {
        return usersMapper.getPasswordByUsername(username);
    }

    @Override
    public Set<String> getRolesByUsername(String username) {
        return usersMapper.getRolesByUsername(username);
    }

    @Override
    public Set<String> getPermissionsByRoleName(Set<String> roles) {
        Map<String,Set> map = new HashMap<String,Set>();
        map.put("roles",roles);
        return usersMapper.getPermissionsByRoleName(map);
    }
}
