package com.pomdetom.notes.mapper;

import com.pomdetom.notes.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestServiceMapper {

    @Select("SELECT * FROM user WHERE user_id = 100000")
    User getUser();

    int insert(User user);
}
