package com.ncf.mybatis;

import com.ncf.mybatis.binding.MapperRegistry;
import com.ncf.mybatis.dao.IUserDao;
import com.ncf.mybatis.session.SqlSession;
import com.ncf.mybatis.session.SqlSessionFactory;
import com.ncf.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ApiTest
 *
 * @Author: fyz
 * @Date: 2023/7/8 14:34
 **/
public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        // 1、注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.ncf.mybatis.dao");

        // 2、从SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3、获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4、测试验证
        String res = userDao.queryUserName("10001");
        logger.info("测试结果： {}", res);
    }
}
