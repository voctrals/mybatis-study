package voctrals.study.mybatis.sqlsessionfactory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 构建sqlSessionFactory方式一
 * <p>
 * 通过SqlSessionFactoryBuilder
 *
 * @author voctrals
 */
public class SqlSessionFactoryBuilderWay {

    public static void main(String[] args) throws IOException {
        // 读取resources下面的配置文件
        String resource = "mybatis-config.xml";

        // Resources是mybatis提供的工具类，能够加载类路径下的资源文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 通过输入流创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取成功
        System.out.println(sqlSessionFactory.getConfiguration().getEnvironment());
    }

}
