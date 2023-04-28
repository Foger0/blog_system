package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static DataSource dataSource=new MysqlDataSource();
    static {
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/blog_system?character=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("dxy19660");

    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
   public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){

        if (resultSet!=null){try {
           resultSet.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        }
       if (statement!=null){
       try {
           statement.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       }
       if (connection!=null){
           try {
               connection.close();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
   }

}
