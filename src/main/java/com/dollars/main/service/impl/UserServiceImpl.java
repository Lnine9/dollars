package com.dollars.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dollars.main.dto.UserDTO;
import com.dollars.main.entity.User;
import com.dollars.main.mappers.UserMapper;
import com.dollars.main.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final ModelMapper modelMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    public UserServiceImpl(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(UserDTO data) {
        User user = modelMapper.map(data, User.class);
        user.setId(null);
        return save(user);
    }

    @Override
    public UserDTO getUserInfo(Long id) {
        User user = getById(id);
        if (user != null){
            user.setPassword(null);
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public UserDTO login(String userName, String password) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("name", userName);
        User user = getOne(qw);
        if (user != null && DigestUtils.md5Hex(user.getPassword()).equals(password)){
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateById(Long id, UserDTO data) {
        User user = modelMapper.map(data, User.class);
        user.setId(id);
        return updateById(user);
    }

    @Override
    public List<UserDTO> getContacts(Long id){
        List<UserDTO> ans = new ArrayList<>();
        List<User> contacts = userMapper.getContacts(id);
        for (User contact : contacts) {
            contact.setPassword(null);
            ans.add(modelMapper.map(contact, UserDTO.class));
        }
        return ans;
    }

    @Override
    public boolean addFriend(Long id1, Long id2){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("id", id2);
        User user = getOne(qw);
        if (user == null){
            return false;
        } else {
            return userMapper.addFriend(id1, id2);
        }
    }
}
