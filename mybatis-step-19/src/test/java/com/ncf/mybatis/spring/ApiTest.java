package com.ncf.mybatis.spring;

import com.alibaba.fastjson.JSON;
import com.ncf.mybatis.spring.dao.IActivityDao;
import com.ncf.mybatis.spring.po.Activity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * FactoryBean 使用案例
 *
 * https://blog.csdn.net/yx444535180/article/details/124605575
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_ClassPathXmlApplicationContext() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        IActivityDao dao = beanFactory.getBean("IActivityDao", IActivityDao.class);
        Activity res = dao.queryActivityById(new Activity(100001L));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }

}
