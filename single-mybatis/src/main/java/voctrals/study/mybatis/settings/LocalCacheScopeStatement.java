package voctrals.study.mybatis.settings;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import voctrals.study.mybatis.entity.Student;
import voctrals.study.mybatis.mapper.StudentMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * mybatis一级缓存之STATEMENT
 * <p>
 * 查询结果不会被cache
 *
 * @author voctrals
 */
public class LocalCacheScopeStatement {

    private static final Logger logger = Logger.getLogger(LocalCacheScopeStatement.class);

    public static void main(String[] args) throws IOException {

        // 读取resources下面的配置文件
        String resource = "mybatis-config-local-cache-statement.xml";

        // Resources是mybatis提供的工具类，能够加载类路径下的资源文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 通过输入流创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

            logger.info("访问数据库获取数据，数据不会cache");
            List<Student> allStudent = studentMapper.getAll();
            allStudent.forEach(System.out::println);

            logger.info("访问数据库获取数据，数据不会cache");
            List<Student> allStudent2 = studentMapper.getAll();
            allStudent2.forEach(System.out::println);

        }
    }
}
