package com.ncf.mybatis.scripting;

import com.ncf.mybatis.mapping.SqlSource;
import com.ncf.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * 脚本语言驱动
 *
 * @Author: fyz
 * @Date: 2023/7/16 13:02
 **/
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

}
