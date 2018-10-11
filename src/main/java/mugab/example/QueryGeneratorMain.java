package mugab.example;

import org.json.JSONObject;

import mugab.dyn.sql.InsertSQLBuilder;
import mugab.dyn.sql.RetrieveSQLBuilder;
import mugab.dyn.sql.UpdateSQLBuilder;
import mugab.dyn.sql.DeleteSQLBuilder;
import mugab.info.service.ServiceDetailBean;

public class QueryGeneratorMain {

	public static void main(String[] args) {
		ServiceDetailBean requestDetail = new ServiceDetailBean();
		
		requestDetail.setServiceName("Registration");
		requestDetail.setServiceJsonProperties(ServiceJSONProperties.getServiceJSON());
		requestDetail.setServiceJsonMapProperties(ServiceJSONMapProperties.getServiceJSONMap());
				
		JSONObject requestJson = new JSONObject();
		requestJson.put("id", "1");
		requestJson.put("name", "Sabah");
		requestJson.put("age", "30");
		requestJson.put("city", "Dhaka");
		requestJson.put("country", "Bangladesh");
		
		InsertSQLBuilder insertQuery = new InsertSQLBuilder();
		System.out.println(insertQuery.getInsertQuery(requestDetail, requestJson));
		
		requestJson.put("id", "1");
		requestJson.put("name", "Sabah");
		requestJson.put("age", "30");
		
		UpdateSQLBuilder updateQuery = new UpdateSQLBuilder();
		System.out.println(updateQuery.getUpdateQuery(requestDetail, requestJson));
		
		requestJson.put("id", "2");
		requestJson.put("name", "Sab");
		
		DeleteSQLBuilder deleteQuery = new DeleteSQLBuilder();
		System.out.println(deleteQuery.getDeleteQuery(requestDetail, requestJson));
		
		requestJson.put("id", "2");
		requestJson.put("age", "15");
		requestJson.put("startage", "10");
		requestJson.put("endage", "30");
		
		RetrieveSQLBuilder retrieveQuery = new RetrieveSQLBuilder();
		System.out.println(retrieveQuery.getSelectQuery(requestDetail, requestJson));
	}
}