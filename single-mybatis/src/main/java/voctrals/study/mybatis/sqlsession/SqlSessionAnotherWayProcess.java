package voctrals.study.mybatis.sqlsession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import voctrals.study.mybatis.entity.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 获取和使用sqlSession
 * <p>
 * 通过statement字符串进行
 *
 * @author voctrals
 */
public class SqlSessionAnotherWayProcess {

    public static void main(String[] args) throws IOException {

        // 读取resources下面的配置文件
        String resource = "mybatis-config.xml";

        // Resources是mybatis提供的工具类，能够加载类路径下的资源文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 通过输入流创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<Person> allPerson = sqlSession.selectList("voctrals.study.mybatis.mapper.PersonMapper.getAllPerson");
            allPerson.forEach(System.out::println);
        }
    }
}
