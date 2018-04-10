package voctrals.study.mybatis.sqlsessionfactory;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 自定义配置configuration
 * <p>
 * 不通过配置文件的方式，而是通过java代码的方式
 *
 * @author voctrals
 */
public class SelfDesignConfigurationWay {

    public static void main(String[] args) {
        // datasource做成
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&useSSL=true");
        properties.setProperty("username", "root");
        properties.setProperty("password", "voctrals");
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();

        // transactionManager配置
        // JDBC     这个配置就是直接使用了 JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务范围。
        // MANAGED  这个配置几乎没做什么。它从来不提交或回滚一个连接，而是让容器来管理事务的整个生命周期
        //          默认情况下它会关闭连接，然而一些容器并不希望这样
        //          因此需要将 closeConnection 属性设置为false来阻止它默认的关闭行为。
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 获取成功
        System.out.println(sqlSessionFactory.getConfiguration().getEnvironment());
    }

}
