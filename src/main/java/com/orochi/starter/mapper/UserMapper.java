package com.orochi.starter.mapper;

import com.orochi.starter.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {

    User findById(@Param("id") long id);

    List<User> findByAge(@Param("age") int age);

    @Override
    int insert(@Param("pojo") User pojo);

    @Override
    int insertSelective(@Param("pojo") User pojo);

    @Override
    int insertList(@Param("pojos") List<User> pojo);

    int update(@Param("pojo") User pojo);
}
