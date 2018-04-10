package voctrals.study.mybatis.settings;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import voctrals.study.mybatis.entity.Person;
import voctrals.study.mybatis.entity.Student;
import voctrals.study.mybatis.mapper.PersonMapper;
import voctrals.study.mybatis.mapper.StudentMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis一级缓存之SESSION
 * <p>
 * 一个sqlSession中不出现更新db时，同样的查询会被cache住，后面的直接从cache中获取数据
 *
 * @author voctrals
 */
public class LocalCacheScopeSession {

    private static final Logger logger = Logger.getLogger(LocalCacheScopeSession.class);

    public static void main(String[] args) throws IOException {

        // 读取resources下面的配置文件
        String resource = "mybatis-config-local-cache-session.xml";

        // Resources是mybatis提供的工具类，能够加载类路径下的资源文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 通过输入流创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

            logger.info("访问数据库获取数据，将数据进行cache");
            List<Student> allStudent = studentMapper.getAll();
            allStudent.forEach(System.out::println);

            logger.info("读取cache中的内容，不重新访问数据库");
            List<Student> allStudent2 = studentMapper.getAll();
            allStudent2.forEach(System.out::println);

            logger.info("更新数据库，cache清空");
            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
            Person person = new Person();
            person.setName("test");
            person.setAge(12);
            personMapper.insert(person);

            logger.info("再次从数据库中获取");
            List<Student> allStudent3 = studentMapper.getAll();
            allStudent3.forEach(System.out::println);

        }
    }
}
