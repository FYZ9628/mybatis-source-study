package com.ncf.mybatis;

import com.alibaba.fastjson.JSON;
import com.ncf.mybatis.builder.xml.XMLConfigBuilder;
import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.session.defaults.DefaultSqlSession;
import com.ncf.mybatis.step04.dao.IUserDao;
import com.ncf.mybatis.io.Resources;
import com.ncf.mybatis.session.SqlSession;
import com.ncf.mybatis.session.SqlSessionFactory;
import com.ncf.mybatis.session.SqlSessionFactoryBuilder;
import com.ncf.mybatis.step04.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

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
        User user = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_selectOne() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration);

        // 执行查询，默认是一个集合参数
        Object[] req = {1L};
        Object res = sqlSession.selectOne("com.ncf.mybatis.step04.dao.IUserDao.queryUserInfoById", req);
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }
}
