package com.qf.service;

import com.qf.pojo.User;

import java.util.List;

public interface UserService {
    public int addUser(User user);

    public int deleteUser(int uid);

    public int updateUser(User user);

    public User getUserByUid(int uid);

    public List<User> getUserList();
}
