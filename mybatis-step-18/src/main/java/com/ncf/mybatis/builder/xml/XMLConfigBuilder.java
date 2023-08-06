package com.ncf.mybatis.builder.xml;

import com.ncf.mybatis.builder.BaseBuilder;
import com.ncf.mybatis.datasource.DataSourceFactory;
import com.ncf.mybatis.io.Resources;
import com.ncf.mybatis.mapping.Environment;
import com.ncf.mybatis.plugin.Interceptor;
import com.ncf.mybatis.session.Configuration;
import com.ncf.mybatis.session.LocalCacheScope;
import com.ncf.mybatis.transaction.TransactionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

/**
 * XML配置构建器，建造者模式，继承BaseBuilder
 *
 * @Author: fyz
 * @Date: 2023/7/8 18:33
 **/
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    /**
     * 将 xml 文件转为 xml 对象
     * 将 mybatis-config-datasource.xml 配置文件信息 转为 xml对象
     *
     * @param reader
     */
    public XMLConfigBuilder(Reader reader) {
        // 1、调用父类初始化Configuration
        super(new Configuration());
        // 2、dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析配置；类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     *
     * @return Configuration
     */
    public Configuration parse() {
        try {
            // 插件 step-16 添加
            pluginElement(root.element("plugins"));
            // 设置
            settingsElement(root.element("settings"));
            // 环境
            // 解析 mybatis-config-datasource.xml 的 environments 节点信息
            environmentsElement(root.element("environments"));
            // 解析映射器
            // 解析 mybatis-config-datasource.xml 的 mappers 节点信息，并读取和解析 mapper.xml 文件
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    /**
     * Mybatis 允许你在某一点切入映射语句执行的调度
     * <plugins>
     *     <plugin interceptor="cn.bugstack.mybatis.test.plugin.TestPlugin">
     *         <property name="test00" value="100"/>
     *         <property name="test01" value="100"/>
     *     </plugin>
     * </plugins>
     */
    private void pluginElement(Element parent) throws Exception {
        if (parent == null) return;
        List<Element> elements = parent.elements();
        for (Element element : elements) {
            String interceptor = element.attributeValue("interceptor");
            // 参数配置
            Properties properties = new Properties();
            List<Element> propertyElementList = element.elements();
            for (Element property : propertyElementList) {
                properties.setProperty(property.attributeValue("name"), property.attributeValue("value"));
            }
            // 获取插件实现类实例化： cn.bugstack.mybatis.test.plugin.TestPlugin
            Interceptor interceptorInstance = (Interceptor) resolveClass(interceptor).newInstance();
            interceptorInstance.setProperties(properties);
            configuration.addInterceptor(interceptorInstance);
        }
    }

    /**
     * <settings>
     *  <!-- 全局缓存：true/false -->
     *  <setting name="cacheEnabled" value="false"/>
     *  <!--缓存级别：SESSION/STATEMENT-->
     *  <setting name="localCacheScope" value="SESSION"/>
     * </settings>
     */
    private void settingsElement(Element context) {
        if (context == null) return;
        List<Element> elements = context.elements();
        Properties props = new Properties();
        for (Element element : elements) {
            props.setProperty(element.attributeValue("name"), element.attributeValue("value"));
        }
        configuration.setCacheEnabled(booleanValueOf(props.getProperty("cacheEnabled"), true));
        configuration.setLocalCacheScope(LocalCacheScope.valueOf(props.getProperty("localCacheScope")));
    }

    /**
     * 解析 mybatis-config-datasource.xml 的 environments 节点信息
     *
     * <environments default="development">
     * <environment id="development">
     * <transactionManager type="JDBC">
     * <property name="..." value="..."/>
     * </transactionManager>
     * <dataSource type="POOLED">
     * <property name="driver" value="${driver}"/>
     * <property name="url" value="${url}"/>
     * <property name="username" value="${username}"/>
     * <property name="password" value="${password}"/>
     * </dataSource>
     * </environment>
     * </environments>
     */
    private void environmentsElement(Element context) throws Exception {
        String environment = context.attributeValue("default");

        List<Element> environmentList = context.elements("environment");
        for (Element e : environmentList) {
            String id = e.attributeValue("id");
            if (environment.equals(id)) {
                // 事务管理器
                TransactionFactory txFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(e.element("transactionManager").attributeValue("type")).newInstance();

                // 数据源
                Element dataSourceElement = e.element("dataSource");
                DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                List<Element> propertyList = dataSourceElement.elements("property");
                Properties props = new Properties();
                for (Element property : propertyList) {
                    props.setProperty(property.attributeValue("name"), property.attributeValue("value"));
                }
                dataSourceFactory.setProperties(props);
                DataSource dataSource = dataSourceFactory.getDataSource();

                // 构建环境
                Environment.Builder environmentBuilder = new Environment.Builder(id)
                        .transactionFactory(txFactory)
                        .dataSource(dataSource);

                configuration.setEnvironment(environmentBuilder.build());
            }
        }
    }

    /**
     * 解析映射器
     * 解析 mybatis-config-datasource.xml 的 mappers 节点信息
     * 并读取和解析 mapper.xml 文件
     *
     * <mappers>
     *	 <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
     *	 <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
     *	 <mapper resource="org/mybatis/builder/PostMapper.xml"/>
     *
     *	 <mapper class="cn.bugstack.mybatis.test.dao.IUserDao"/>
     * </mappers>
     *
     * @param mappers
     */
    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element e : mapperList) {
            String resource = e.attributeValue("resource");
            String mapperClass = e.attributeValue("class");
            // XML 解析
            if (resource != null && mapperClass == null) {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                // 在for循环里每个mapper都重新new一个XMLMapperBuilder，来解析
                XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(inputStream, configuration, resource);
                mapperBuilder.parse();
            }
            // Annotation 注解解析
            else if (resource == null && mapperClass != null) {
                Class<?> mapperInterface = Resources.classForName(mapperClass);
                configuration.addMapper(mapperInterface);
            }

        }
    }
}
