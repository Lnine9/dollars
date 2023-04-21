package com.dollars.main.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dollars.main.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select user.* from user join friendship on id = user2_id where user1_id = #{id}")
    List<User> getContacts(Long id);

    @Insert("insert into friendship values (#{id1},#{id2}), (#{id2},#{id1})")
    boolean addFriend(Long id1, Long id2);

}
