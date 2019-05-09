// this class handles updates for databas
package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

    public  ResultSet executeUpdate(Connection connection, String sqliteStatement) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = connection.createStatement().executeQuery(sqliteStatement);
       // connection.close();
        return resultSet;
    }

}
