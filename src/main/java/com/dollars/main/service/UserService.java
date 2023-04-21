package com.dollars.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dollars.main.dto.UserDTO;
import com.dollars.main.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    boolean add(UserDTO data);

    UserDTO getUserInfo(Long id);

    UserDTO login(String userName, String password);

    boolean deleteById(Long id);

    boolean updateById(Long id, UserDTO data);

    List<UserDTO> getContacts(Long id);

    boolean addFriend(Long id1, Long id2);
}
