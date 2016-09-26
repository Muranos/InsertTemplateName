package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.sun.rowset.*;

public class Connector {

	public static ResultSet getResultSet(Connection connection, String query) throws SQLException {
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rSet = statement.executeQuery(query);
		ResultFormatter.printSqlWarnings(rSet.getWarnings());
		return rSet;
	}

	public static JdbcRowSetImpl getJdbcRowSet(Connection connection, String query) throws SQLException {
		JdbcRowSetImpl jdbcSet = new JdbcRowSetImpl(connection);
		jdbcSet.setCommand(query);
		jdbcSet.execute();
		ResultFormatter.printSqlWarnings(jdbcSet.getWarnings());
		return jdbcSet;
	}

	public static CachedRowSetImpl getCachedRowSet(String url, String user, String password, String query) throws SQLException {
		CachedRowSetImpl cSet = new CachedRowSetImpl();
		setUpCachedRowSet(cSet, url, user, password, query);
		return cSet;
	}

	public static WebRowSetImpl getWebRowSet(String url, String user, String password, String query) throws SQLException {
		WebRowSetImpl cSet = new WebRowSetImpl();
		setUpCachedRowSet(cSet, url, user, password, query);
		return cSet;
	}

	public static JoinRowSetImpl getJoinRowSet() throws SQLException {
		JoinRowSetImpl jSet = new JoinRowSetImpl();
		return jSet;
	}

	private static void setUpCachedRowSet(CachedRowSetImpl cSet, String url, String user, String password, String query) throws SQLException {
		cSet.setUrl(url);
		cSet.setUsername(user);
		cSet.setPassword(password);
		cSet.setCommand(query);
		cSet.setKeyColumns(new int[] { 1 });
		cSet.execute();
		ResultFormatter.printSqlWarnings(cSet.getWarnings());
	}

	public static Connection getLiveReadOnlyConnection() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "parse");
		prop.put("password", "ga9ruTr8");
		prop.put("server", "LIVE (Read Only)");
		return DriverManager.getConnection("jdbc:postgresql://prod-ddb2.int.parchment.com/dfdata", prop);
	}

	public static Connection getLiveFullConnection() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "parse");
		prop.put("password", "ga9ruTr8");
		prop.put("server", "LIVE (Full)");
		return DriverManager.getConnection("jdbc:postgresql://prod-ddb1.int.parchment.com/dfdata", prop);
	}

	public static Connection getTsunamiConnection() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "docufide");
		prop.put("password", "jeeW1ope");
		prop.put("server", "Tsunami");
		return DriverManager.getConnection("jdbc:postgresql://tsunami:5432/ia", prop);
	}

	public static Connection getQaexConnection() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "docufide");
		prop.put("password", "sectranqa");
		prop.put("server", "QAEX");
		return DriverManager.getConnection("jdbc:postgresql://sdb01b.int.parchment.com/dfdata", prop);
	}

	public static Connection getBetaConnection() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "docufide");
		prop.put("password", "sectranqa");
		prop.put("server", "BETA");
		return DriverManager.getConnection("jdbc:postgresql://betadb1a.int.parchment.com:5432/dfdata", prop);
	}

	public static Connection getIntConnection() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "docufide");
		prop.put("password", "sectranqa");
		prop.put("server", "INT");
		return DriverManager.getConnection("jdbc:postgresql://intdb01a.int.parchment.com:5432/dfdata", prop);
	}

	public static Connection getRabbitConnection() throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "docufide");
		prop.put("password", "jeeW1ope");
		prop.put("server", "Rabbit");
		return DriverManager.getConnection("jdbc:postgresql://rabbit:5432/ia", prop);
	}
}
