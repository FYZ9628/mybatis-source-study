package com.ncf.mybatis.builder.xml;

import com.ncf.mybatis.builder.BaseBuilder;
import com.ncf.mybatis.builder.MapperBuilderAssistant;
import com.ncf.mybatis.mapping.SqlCommandType;
import com.ncf.mybatis.mapping.SqlSource;
import com.ncf.mybatis.scripting.LanguageDriver;
import com.ncf.mybatis.session.Configuration;
import org.dom4j.Element;

import java.util.Locale;

/**
 * XML语句构建器
 *
 * @Author: fyz
 * @Date: 2023/7/16 13:08
 **/
public class XMLStatementBuilder extends BaseBuilder {

    private MapperBuilderAssistant builderAssistant;
    private Element element;

    public XMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, Element element) {
        super(configuration);
        this.builderAssistant = builderAssistant;
        this.element = element;
    }

    /**
     * 解析语句(select|insert|update|delete)
     *
     * <select
     *   id="selectPerson"
     *   parameterType="int"
     *   parameterMap="deprecated"
     *   resultType="hashmap"
     *   resultMap="personResultMap"
     *   flushCache="false"
     *   useCache="true"
     *   timeout="10000"
     *   fetchSize="256"
     *   statementType="PREPARED"
     *   resultSetType="FORWARD_ONLY">
     *   SELECT * FROM PERSON WHERE ID = #{id}
     * </select>
     *
     */
    public void parseStatementNode() {
        String id = element.attributeValue("id");
        // 参数类型
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveAlias(parameterType);
        // 外部应用 resultMap
        String resultMap = element.attributeValue("resultMap");
        // 结果类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        // 获取命令类型（select|insert|update|delete）
        String nodeName = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));

        // 获取默认语言驱动器
        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);

        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        // 调用助手类【本节新添加，便于统一处理参数的包装】
        builderAssistant.addMappedStatement(
                id,
                sqlSource,
                sqlCommandType,
                parameterTypeClass,
                resultMap,
                resultTypeClass,
                langDriver
        );
    }
}
