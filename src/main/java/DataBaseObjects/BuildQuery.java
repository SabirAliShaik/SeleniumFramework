package DataBaseObjects;

import java.util.List;
import java.util.Map;

public class BuildQuery {

	static String queryString;
	
	public static List<Map<String, Object>> getPODetails() {
		queryString = "Select PROCESSING_ORG_ID, PROCESSING_ORG_NAME, PO_BATCH_REFERENCE from Processing_org";
		return DataBase.executeQuery(queryString);
	}
	public static String getFirmName(String firmId) {
		queryString = "select firm_name from firm where firm_id ='"+firmId+"'";
		return DataBase.executeQuery(queryString).get(0).get("FIRM_NAME").toString();
	}
}
