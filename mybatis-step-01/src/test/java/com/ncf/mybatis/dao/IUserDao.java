package com.ncf.mybatis.dao;

/**
 * IUserDao
 *
 * @Author: fyz
 * @Date: 2023/7/8 12:14
 **/
public interface IUserDao {
    String queryUserName(String uId);

    Integer queryUserAge(String uId);
}
