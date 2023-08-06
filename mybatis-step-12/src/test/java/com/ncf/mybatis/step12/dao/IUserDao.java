package com.ncf.mybatis.step12.dao;

import com.ncf.mybatis.annotations.Delete;
import com.ncf.mybatis.annotations.Insert;
import com.ncf.mybatis.annotations.Select;
import com.ncf.mybatis.annotations.Update;
import com.ncf.mybatis.step12.po.User;

import java.util.List;

public interface IUserDao {

    @Select("SELECT id, userId, userName, userHead, createTime \n" +
            "FROM user\n" +
            "where id = #{id}")
    User queryUserInfoById(Long id);

    @Select("SELECT id, userId, userName, userHead, createTime \n" +
            "        FROM user\n" +
            "        where id = #{id}")
    User queryUserInfo(User req);

    @Select("SELECT id, userId, userName, userHead, createTime \n" +
            "FROM user")
    List<User> queryUserInfoList();

    @Update("UPDATE user\n" +
            "SET userName = #{userName}\n" +
            "WHERE id = #{id}")
    int updateUserInfo(User req);

    @Insert("INSERT INTO user\n" +
            "(userId, userName, userHead, createTime, updateTime)\n" +
            "VALUES (#{userId}, #{userName}, #{userHead}, now(), now())")
    void insertUserInfo(User req);

    @Delete("DELETE FROM user WHERE userId = #{userId}")
    int deleteUserInfoByUserId(String userId);

}
