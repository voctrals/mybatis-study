package voctrals.study.mybatis.sqlsession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import voctrals.study.mybatis.entity.Person;
import voctrals.study.mybatis.mapper.PersonMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 获取和使用sqlSession
 * <p>
 * getMapper处理方式
 *
 * @author voctrals
 */
public class SqlSessionGetMapperProcess {

    public static void main(String[] args) throws IOException {

        // 读取resources下面的配置文件
        String resource = "mybatis-config.xml";

        // Resources是mybatis提供的工具类，能够加载类路径下的资源文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 通过输入流创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
            List<Person> allPerson = personMapper.getAll();
            allPerson.forEach(System.out::println);
        }
    }
}
