package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

public class InsertIntoParseTemplate {
	private final static java.io.PrintStream o = java.lang.System.out;
	private static final String template = "PdfPesc.HS.IndianaConnectionsXML";
	private static final String technology = "java";
	private static final boolean hasIdStage = false;
	private static final String username = "mvasilenko";

	public static void main(String[] args) throws SQLException, IOException {
			String query = QueryProvider.toRegularQuery(QueryProvider.getInsertIntoParseTemplateIdempotentQuery(), template, technology, hasIdStage);
			insertIntoDatabase(Connector.getLiveFullConnection(), template, query);
			writeDbmodsFile(query, template, username);
	}

	public static int insertIntoDatabase(Connection connection, String template, String query) throws SQLException {
		try {
			ResultFormatter.printConnectionPropertis(connection);
			return runInsert(connection, template, query);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	   public static int updateInDatabase(Connection connection, String template, String query) throws SQLException {
	        try {
	            ResultFormatter.printConnectionPropertis(connection);
	            return runUpdate(connection, template, query);
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }

	public static String writeDbmodsFile(String query, String template, String username) throws IOException {
		//final String PATH = "D:\\svn.docufide.com\\parse_dbmods\\";
		final String PATH = "D:\\svn.docufide.com\\dbmods\\PostGreSQL\\Delta\\parse\\";
		final String DATE = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		String fileName = String.format("%sdbmods_%s_%s_%s.sql", PATH, DATE, template, username);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
			pw.write(query);
			String message = fileName + " created";
			o.println(message);
			return message;
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	private static int runInsert(Connection connection, String template, String query) throws SQLException {
		Statement isAlreadyPresent = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = isAlreadyPresent.executeQuery(String.format("SELECT * FROM parse_template WHERE pt_name = '%s'", template));
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultFormatter.printSqlWarnings(statement.getWarnings());
		//String query = QueryProvider.toRegularQuery(QueryProvider.getInsertIntoParseTemplateIdempotentQuery(), template, technology, hasIdStage);
		if (resultSet.last()) {
			System.err.format("'%s' is already present in the database%n", template);
			return 0;
		}
		int result = statement.executeUpdate(query);
		if (result == 1) {
			o.format("'%s' added to parse_template table%n", template);
			return 1;
		} else {
			System.err.format("'%s' was not addet to parse_template table%n", template);
			return -1;
		}
	}
	
	   private static int runUpdate(Connection connection, String template, String query) throws SQLException {
	        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        ResultFormatter.printSqlWarnings(statement.getWarnings());
	        int result = statement.executeUpdate(query);
	        if (result == 1) {
	            o.format("'%s' updated int parse_template table%n", template);
	            return 1;
	        } else {
	            System.err.format("'%s' was not updated in parse_template table%n", template);
	            return -1;
	        }
	    }
}
