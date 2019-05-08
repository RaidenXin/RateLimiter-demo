package com.huihuang.mapper;


import com.huihuang.model.User;
import org.apache.ibatis.annotations.Insert;

public interface UserMapper {

    @Insert("INSERT INTO user (ID,USER_NAME,PASS_WORD) VALUES('id',#{userName},#{password})")
    public void save(User user);
}
