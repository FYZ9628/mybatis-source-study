package com.ncf.mybatis.scripting.xmltags;

import com.ncf.mybatis.mapping.SqlSource;
import com.ncf.mybatis.scripting.LanguageDriver;
import com.ncf.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * XML语言驱动器
 *
 * @Author: fyz
 * @Date: 2023/7/16 13:05
 **/
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

}
