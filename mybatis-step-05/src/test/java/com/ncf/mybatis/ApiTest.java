package com.ncf.mybatis;

import com.alibaba.fastjson.JSON;
import com.ncf.mybatis.builder.xml.XMLConfigBuilder;
import com.ncf.mybatis.datasource.pooled.PooledDataSource;
import com.ncf.mybatis.io.Resources;
import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.session.SqlSession;
import com.ncf.mybatis.session.SqlSessionFactory;
import com.ncf.mybatis.session.SqlSessionFactoryBuilder;
import com.ncf.mybatis.session.defaults.DefaultSqlSession;
import com.ncf.mybatis.step04.dao.IUserDao;
import com.ncf.mybatis.step04.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ApiTest
 *
 * @Author: fyz
 * @Date: 2023/7/8 18:26
 **/
public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1、从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2、获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3、测试验证
        for (int i = 0; i < 50; i++) {
            User user = userDao.queryUserInfoById(1L);
            logger.info("测试结果：{}", JSON.toJSONString(user));
        }
    }

    @Test
    public void test_pooled() throws SQLException, InterruptedException {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
//        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull&amp;useSSL=true&amp;serverTimezone=GMT%2B8&amp;autoReconnect=true&amp;rewriteBatchedStatements=true");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&autoReconnect=true&rewriteBatchedStatements=true");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("root");
        // 持续获得链接
        while (true) {
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
//            connection.close();
        }
    }
}
