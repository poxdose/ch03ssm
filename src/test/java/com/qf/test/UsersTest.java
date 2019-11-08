package com.qf.test;

import com.qf.mapper.UsersMapper;
import com.qf.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mapper.xml","classpath:applicationContext.xml"})
public class UsersTest {


    @Autowired
    private UsersService usersService;

    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    private UsersMapper usersMapper;

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Test
    public void testLogin(){
        String password = usersMapper.getPasswordByUsername("admin");
        System.out.println(password);
    }

    @Test
    public void testRoles(){
        Set<String> roles = usersMapper.getRolesByUsername("admin");
        for (String s : roles){
            System.out.println(s);
        }
    }

    @Test
    public void testPermissions(){
        Set<String> roles = new HashSet<String>();
        roles.add("role1");
        roles.add("role2");
        Map<String,Set> map = new HashMap<String,Set>();
        map.put("roles",roles);
        Set<String> permissions = usersMapper.getPermissionsByRoleName(map);
        for(String s : permissions){
            System.out.println(s);
        }
    }

    @Test
    public void testRealm(){
        String password = usersService.getPasswordByUsername("admin");
        System.out.println(password);
    }
}
