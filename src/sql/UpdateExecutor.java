package sql;

import java.sql.*;
import java.util.Properties;

import javax.sql.rowset.*;

public class UpdateExecutor {
	private final static java.io.PrintStream o = java.lang.System.out;
	private static final int MIN_TEST_PKID = 2833896;

	public static void main(String[] args) throws java.io.IOException {
		Connection connection = null;
		try {
			connection = Connector.getRabbitConnection();
			String query = "select * from organization where pkid >= " + Integer.toString(MIN_TEST_PKID);
			Properties prop = new Properties();
			prop.put("url", "jdbc:postgresql://rabbit:5432/ia");
			prop.put("user", "docufide");
			prop.put("password", "jeeW1ope");
			prop.put("command", query);
			prop.storeToXML(new java.io.FileOutputStream("D:\\Temp\\Prop.xml"), "");
			WebRowSet result = Connector.getWebRowSet(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"), query);
			result.readXml(new java.io.FileReader("D:\\Temp\\ResultSet.xml"));
			//prop.loadFromXML(new java.io.FileInputStream("D:\\Beary\\grizzly_3_10\\mapping\\hibernate_rabbit.cfg.xml"));
			ResultFormatter.formatResultSet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				o.println("Exception while closing the connection\n");
				e.printStackTrace();
			}
		}
	}

	private static int updateRowSet(ResultSet result) throws SQLException {
		int maxPkid = 0;
		while (result.next()) {
			if (result.getInt("pkid") > maxPkid) {
				maxPkid = result.getInt("pkid");
			}
			result.updateString("printname", ResultFormatter.randomize(15));
			result.updateInt("addressfk", (int) Math.round(Math.random() * 300));
			result.updateRow();
		}
		return maxPkid;
	}

	private static void insertRow(ResultSet result, int pkid) throws SQLException {
		result.moveToInsertRow();
		result.updateInt("pkid", pkid);
		result.updateString("name", "Insert Higher Purpose Ed");
		result.updateString("printname", "Not for Print");
		result.insertRow();
		result.moveToCurrentRow();
	}

}
