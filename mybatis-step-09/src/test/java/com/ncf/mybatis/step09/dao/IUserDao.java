package com.ncf.mybatis.step09.dao;

import com.ncf.mybatis.step09.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

    User queryUserInfo(User req);
}
