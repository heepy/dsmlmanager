package com.horan.dsmlmanager.dao;

import com.horan.dsmlmanager.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

   List<User> selectAllUser();
}
