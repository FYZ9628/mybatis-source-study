package com.ncf.mybatis.dao;

import com.ncf.mybatis.po.Activity;

import java.util.List;

public interface IActivityDao {

    Activity queryActivityById(Activity activity);

    List<Activity> queryActivityList(Activity activity);
}
