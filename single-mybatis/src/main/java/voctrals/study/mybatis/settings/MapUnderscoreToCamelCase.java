package voctrals.study.mybatis.settings;

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
 * 下划线转化为驼峰
 * <p>
 * sql中的teacher_name映射为teacherName
 *
 * @author voctrals
 */
public class MapUnderscoreToCamelCase {

    public static void main(String[] args) throws IOException {

        // 读取resources下面的配置文件
        String resource = "mybatis-config.xml";

        // Resources是mybatis提供的工具类，能够加载类路径下的资源文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 通过输入流创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> allStudent = studentMapper.getAll();
            allStudent.forEach(System.out::println);
        }
    }
}
