package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.io.*;

public class ResultFormatter {
	private final static java.io.PrintStream o = java.lang.System.out;
	private static final int PKID_WIDTH = 10;
	private static final int TEMPLATE_WIDTH = 40;
	private static final int DATE_WIDTH = 15;

	private static void printAllColumns(ResultSet result, boolean isHeading) throws SQLException {
		ResultSetMetaData rsmd = result.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int i = 1; i <= columns; i++) {
			int size = rsmd.getColumnDisplaySize(i);
			String columnName = rsmd.getColumnName(i);
			columnName = columnName.length() < size ? columnName : columnName.substring(0, size - 1);
			o.printf("%-" + (size > TEMPLATE_WIDTH ? TEMPLATE_WIDTH : size) + (isHeading ? "S" : "s")
					+ (i == columns ? "%n" : ""), (isHeading ? columnName : result.getObject(i)));
		}
	}

	public static String randomize(int length) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i <= length; i++) {
			result.append((char) Math.round(Math.random() * 100));
		}
		return result.toString();
	}

	public static void printConnectionPropertis(Connection connection) throws SQLException {
		DatabaseMetaData dmb = connection.getMetaData();
		String url = dmb.getURL();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy\tHH:mm");
		Formatter frmt = new Formatter();
		String name = "";
		if (url.contains("sdb03a")) {
			name = "LIVE";
		} else if (url.contains("tsunami:5432/ia")) {
			name = "TSUNAMI";
		} else if (url.contains("sdb01b")) {
			name = "QAEX";
		} else if (url.contains("rabbit:5432/ia")) {
			name = "rabbit";
		}
		frmt.format("%nConnected to %s (%s)%n", url, name);
		frmt.format("Current time: %s%n", sdf.format(date));
		o.println(frmt);
		log(frmt);
		// boolean isConcurrentUpdatable = dmb.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// o.printf("Supports concurrent updates: %b%n", isConcurrentUpdatable);
	}

	private static void log(Formatter frmt) {
		FileWriter file = null;
		BufferedWriter bw = null;
		try {
			file = new FileWriter("D:\\Temp\\Chemeketa.log", true);
			bw = new BufferedWriter(file);
			bw.write(frmt.toString());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			frmt.close();
			try {
				if (file != null) {
					file.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void formatChemeketaResult(ResultSet result) throws SQLException {
		Formatter fr = new Formatter();
		if (result.last()) {
			fr.format("%-" + DATE_WIDTH + "s" + "%-" + TEMPLATE_WIDTH + "s" + "%-" + PKID_WIDTH + "s" + "%-" + PKID_WIDTH + "s"
					+ "%-" + TEMPLATE_WIDTH + "s" + "%-" + PKID_WIDTH + "s" + "%-" + PKID_WIDTH + "s"
					+ "%n",
					"Date", "File Name", "TID", "RIFK", "School", "Data", "Delivery Method");
		} else {
			fr.format("\tNo backup orders%n");
		}
		result.beforeFirst();
		while (result.next()) {
			fr.format("%-" + DATE_WIDTH + "s", result.getDate("creationdate"));
			fr.format("%-" + TEMPLATE_WIDTH + "s", result.getString("rawuploadfile"));
			fr.format("%-" + PKID_WIDTH + "s", result.getString("tid"));
			fr.format("%-" + PKID_WIDTH + "s", result.getString("rifk"));
			fr.format("%-" + TEMPLATE_WIDTH + "s", result.getString("name"));
			fr.format("%-" + PKID_WIDTH + "s", result.getString("dataformat"));
			fr.format("%-" + PKID_WIDTH + "s%n", result.getString("deliverymethod"));
		}
		o.print(fr);
		log(fr);
	}

	public static void formatChemeketaResult2(ResultSet result) throws SQLException {
		Formatter fr = new Formatter();
		if (result.last()) {
			fr.format("%-" + PKID_WIDTH + "s" + "%-" + TEMPLATE_WIDTH + "s" + "%-" + DATE_WIDTH + "s" + "%-" + DATE_WIDTH + "s"
					+ "%-" + DATE_WIDTH + "s"
					+ "%-" + 150 + "s"
					+ "%n",
					"PKID", "File Name", "Starttime", "Lastruntime", "Name", "Description");

		} else {
			fr.format("\tNo failed events%n");
		}
		result.beforeFirst();
		while (result.next()) {
			fr.format("%-" + PKID_WIDTH + "s", result.getInt("pkid"));
			fr.format("%-" + TEMPLATE_WIDTH + "s", result.getString("input"));
			fr.format("%-" + DATE_WIDTH + "s", result.getDate("starttime"));
			fr.format("%-" + DATE_WIDTH + "s", result.getDate("lastruntime"));
			fr.format("%-" + DATE_WIDTH + "s%n", result.getString("name"));
			fr.format("%-" + 150 + "s%n", result.getString("eventfaileddesc"));
		}

		o.print(fr);
		log(fr);
	}

	public static void formatResultSet(ResultSet result) throws SQLException {
		if (!result.last()) {
			o.println("No records found");
			return;
		}
		result.beforeFirst();
		printAllColumns(result, true);
		while (result.next()) {
			printAllColumns(result, false);
		}
	}

	public static void printSqlWarnings(SQLWarning warning) {
		while (warning != null) {
			o.println("Warning!\tError code: " + warning.getErrorCode());
			warning = warning.getNextWarning();
		}
	}
}
