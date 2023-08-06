package com.ncf.mybatis.dao;

import com.ncf.mybatis.po.Activity;

public interface IActivityDao {

    Activity queryActivityById(Long activityId);

    Integer insert(Activity activity);
}
