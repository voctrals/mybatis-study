package voctrals.study.mybatis.jdbcway;


import voctrals.study.mybatis.jdbcway.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 基础PreparedStatement学习
 * <p>
 * 太繁杂了，这话是我说的，不专业
 *
 * @author voctrals
 */
public class BasicJdbcPreparedStatementMain {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        PreparedStatement preparedStatement = null;
        try {

            // 创建数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&useSSL=true",
                    "root",
                    "voctrals"
            );
            System.out.println("数据库连接成功!");

            // statement way, normal sql without parameters to pass
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, name, teacher_name FROM student;");
            while (resultSet.next()) {
                Student student = new Student();
                student.setName(resultSet.getString("name"));
                student.setTeacherName(resultSet.getString("teacher_name"));
                System.out.println(student);
            }

            // prepare statement way
            preparedStatement = conn.prepareStatement("SELECT id, name, teacher_name FROM student WHERE id = ?;");
            preparedStatement.setString(1, "123");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setName(resultSet.getString("name"));
                student.setTeacherName(resultSet.getString("teacher_name"));
                System.out.println(student);
            }

            preparedStatement.setString(1, "345");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setName(resultSet.getString("name"));
                student.setTeacherName(resultSet.getString("teacher_name"));
                System.out.println(student);
            }

        } catch (SQLException e) {
            System.out.println("数据库连接失败!");
            e.printStackTrace();
        } finally {
            try {
                //关闭数据库
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("数据库关闭失败!");
            }
        }
    }
}