package com.ncf.mybatis.step04.dao;

import com.ncf.mybatis.step04.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

}
