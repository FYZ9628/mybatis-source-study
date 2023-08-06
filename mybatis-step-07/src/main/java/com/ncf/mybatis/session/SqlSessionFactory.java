package com.ncf.mybatis.session;

/**
 * 工厂模式接口，构建SqlSession的工厂
 *
 * @Author: fyz
 * @Date: 2023/7/8 14:32
 **/
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     *
     * @return
     */
    SqlSession openSession();
}
