package com.ncf.mybatis;

import com.alibaba.fastjson.JSON;
import com.ncf.mybatis.binding.MapperProxyFactory;
import com.ncf.mybatis.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * ApiTest
 *
 * @Author: fyz
 * @Date: 2023/7/8 12:17
 **/
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.ncf.mybatis.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中的sql语句，操作：查询用户名称");

        IUserDao userDao = factory.newInstance(sqlSession);
        String result = userDao.queryUserName("1");
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }

    @Test
    public void test_proxy_class() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[] {IUserDao.class},
                ((proxy, method, args) -> "你被代理了！")
        );
        String result = userDao.queryUserName("1");
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }
}
