package by.darchyk.task1.resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManager {

	public Connection getConnection(Properties properties) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", properties);
		} catch (SQLException e) {
			throw new ExceptionInInitializerError();
		}

		return connection;
	}
}