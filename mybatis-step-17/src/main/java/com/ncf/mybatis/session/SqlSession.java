package com.ncf.mybatis.session;

import java.util.List;

/**
 * SqlSession 用来执行SQL，获取映射器，管理事务。
 * PS：通常情况下，我们在应用程序中使用的Mybatis的API就是这个接口定义的方法。
 *
 * @Author: fyz
 * @Date: 2023/7/8 14:31
 **/
public interface SqlSession {

    /**
     * 根据指定的SqlID获取一条记录的封装对象
     *
     * @param statement
     * @return
     * @param <T> 封装之后的对象类型
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定的SqlID获取一条记录的封装对象，只不过这个方法容许我们可以给sql传递一些参数
     * 一般在实际使用中，这个参数传递的是pojo，或者Map或者ImmutableMap
     *
     * @param statement
     * @param parameter
     * @return
     * @param <T>
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 获取多条记录，这个方法容许我们可以传递一些参数
     *
     * @param statement
     * @param parameter
     * @return
     * @param <E>
     */
    <E> List<E> selectList(String statement, Object parameter);

    /**
     * 插入记录，容许传入参数。
     *
     * @param statement
     * @param parameter
     * @return
     */
    int insert(String statement, Object parameter);

    /**
     * 更新记录
     *
     * @param statement
     * @param parameter
     * @return
     */
    int update(String statement, Object parameter);

    /**
     * 删除记录
     *
     * @param statement
     * @param parameter
     * @return
     */
    Object delete(String statement, Object parameter);

    /**
     * 以下是事务控制方法 commit,rollback
     */
    void commit();

    /**
     * 关闭 Session
     */
    void close();

    /**
     * 清理 Session 缓存
     */
    void clearCache();

    /**
     * 获取配置
     *
     * @return
     */
    Configuration getConfiguration();

    /**
     * 得到映射器，这个巧妙的使用了泛型，使得类型安全
     *
     * @param type
     * @return
     * @param <T>
     */
    <T> T getMapper(Class<T> type);
}
