package sql;

import org.apache.commons.lang.StringUtils;


public class QueryProvider {
	private static String linesToQuery(String[] lines) {
		StringBuilder sb = new StringBuilder();
		for (String string : lines) {
			sb.append(string);
		}
		return sb.toString().trim();
	}

	public static String toRegularQuery(String query, Object... param) {
		for (Object current : param) {
			current = current instanceof String || current == null ? "'" + current + "'" : current;
			query = StringUtils.replaceOnce(query, "?", current.toString());
		}
		return query;
	}

	public static String getChemeketaQuery() {
		String[] lines = {
				"SELECT",
				" dx.creationdate,",
				" dx.rawuploadfile,",
				" td.tid,",
				" td.rifk,",
				" o.name,",
				" ro.dataformat,",
				" td.deliverymethod ",
				"FROM",
				" wfevents wf",
				" JOIN detailxml dx ON wf.detailxmlfk = dx.pkid",
				" JOIN rhtranscriptrecord rh ON rh.detailxmlfk = dx.pkid",
				" JOIN transcriptorder tro ON tro.rhtranscriptrecordfk = rh.pkid",
				" JOIN transcriptdelivery td ON td.orderfk = tro.pkid",
				" JOIN membr M ON td.rifk = M .pkid",
				" JOIN organization o ON M .organizationfk = o.pkid",
				" JOIN receiveoptions ro ON ro.membrfk = m.pkid\n",
				"WHERE",
				" dx.membrfk = " + "?",
				" AND (wf.status = " + "?" + "",
				" OR not wf.status_pesc = " + "?" + ")",
				" AND(",
				" creationdate + INTERVAL " + "?" + "",
				")> now()",
		};
		return linesToQuery(lines);
	}

	public static String getChemeketaQuery2() {
		String[] lines = {
				"select pkid, input, starttime, lastruntime, name, eventfaileddesc from wfevents",
				" where input like " + "?" + " and (status = " + "?" + " or not status_pesc = " + "?" + ") and now() - starttime < " + "?" + "",
		};
		return linesToQuery(lines);
	}
	
	public static String getInsertIntoParseTemplateQuery() {
		String[] lines = {
				"INSERT INTO parse_template (\n",
				"\tpt_name,\n",
				"\tpt_technology,\n",
				"\tpt_has_identify_stage,\n",
				"\tpt_create_date,\n",
				"\tpt_is_exchange\n",
				")\n",
				"VALUES (\n",
				"\t?,\n",
				"\t?,\n",
				"\t?,\n",
				"\tnow(),\n",
				"\tfalse\n",
				")",
		};
		return linesToQuery(lines);
	}
	
	public static String getInsertIntoParseTemplateIdempotentQuery() {
		String[] lines = {
				"INSERT INTO parse_template (\n",
				"\tpt_name,\n",
				"\tpt_technology,\n",
				"\tpt_has_identify_stage,\n",
				"\tpt_create_date,\n",
				"\tpt_is_exchange,\n",
				"\tpt_has_divide_stage,\n",
				"\tpt_has_parse_stage\n",
				")\n",
				"SELECT rows_to_insert.* FROM (\n",
				"VALUES (\n",
				"\t?,\n",
				"\t?,\n",
				"\t?,\n",
				"\tnow(),\n",
				"\t?,\n",
				"\t?,\n",
				"\t?\n",
				")\n",
				") AS rows_to_insert (pt_name, pt_technology, pt_has_identify_stage, pt_create_date, pt_is_exchange)\n",
				"LEFT OUTER JOIN parse_template USING (pt_name)\n",
				"WHERE parse_template.pt_id is NULL;"
		};
		return linesToQuery(lines);
	}
	
	   public static String getUpdateParseTemplateQuery() {
	        String[] lines = {
	                "UPDATE parse_template\n",
	                "SET\n",
	                "\tpt_name = ?,\n",
	                "\tpt_technology = ?,\n",
	                "\tpt_has_identify_stage = ?,\n",
	                "\tpt_is_exchange = ?,\n",
	                "\tpt_has_divide_stage = ?,\n",
	                "\tpt_has_parse_stage = ?\n",
	                "WHERE\n",
	                "\tpt_name = ?"
	        };
	        return linesToQuery(lines);
	    }

}
