package voctrals.study.mybatis.multiDatasource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import voctrals.study.mybatis.entity.Student;
import voctrals.study.mybatis.mapper.StudentMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis多数据源问题
 * <p>
 * 主要是sqlSessionFactory的获取时，需要重新打开流再次build一次
 *
 * @author voctrals
 */
public class MultiDataSourceTry {

    public static void main(String[] args) throws IOException {

        // 读取resources下面的配置文件
        String resource = "mybatis-multi-datasource.xml";

        // Resources是mybatis提供的工具类，能够加载类路径下的资源文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 通过输入流创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // inputStream已经在build的过程中被close掉，只能重新获取一次
        InputStream inputStream2 = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory2 = new SqlSessionFactoryBuilder().build(inputStream2, "development2");

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> allStudent = studentMapper.getAll();
            allStudent.forEach(System.out::println);
        }

        try (SqlSession sqlSession = sqlSessionFactory2.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> allStudent = studentMapper.getAll();
            allStudent.forEach(System.out::println);
        }
    }
}
