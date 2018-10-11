/**
 * The MIT License
 * Copyright (c) 2016-2018 Sabah Mohammad Mugab
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 **/

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;
import mugab.dyn.sql.DeleteSQLBuilder;
import mugab.dyn.sql.InsertSQLBuilder;
import mugab.dyn.sql.RetrieveSQLBuilder;
import mugab.dyn.sql.UpdateSQLBuilder;
import mugab.factory.JSONMapperFactory;
import mugab.info.service.ServiceDetailBean;

/**
 * Description: This class is to do the unit testing of the application functionality. 
 **/

public class QueryGeneratorMainTest {

	JSONMapperFactory mapperFactory = new JSONMapperFactory();
	ServiceDetailBean requestDetail = new ServiceDetailBean();
	
	private void setRequestParam(){
		requestDetail.setServiceName("Registration");
		requestDetail.setServiceJsonProperties(TestProperties.getServiceJSONProperties());
		requestDetail.setServiceJsonMapProperties(TestProperties.getServiceJSONMapProperties());
	}
	
	@Test
	public void testRetieveQueryBuilder(){
		
		setRequestParam();	
		requestDetail.setServiceType("Retrieve");
		
		System.out.println("Checking if sql is generated properly for retrieve request...");
			
		String selectSQL = "SELECT \"user_id\",\"name\",\"city\",\"country\" FROM \"user_registration\" WHERE \"user_id\" = 2 AND \"age\" >= 15";
		
		JSONObject requestJson = new JSONObject();
		requestJson.put("id", "2");
		requestJson.put("age", "15");		
		
		RetrieveSQLBuilder retrieveQuery = new RetrieveSQLBuilder();		
		assertTrue(retrieveQuery.getSelectQuery(requestDetail, requestJson).equals(selectSQL));
		
		System.out.println("Retrieve query properly generated.");
		System.out.println("Done.\n");
	}
	
	@Test
	public void testInsertQueryBuilder(){
		
		setRequestParam();	
		requestDetail.setServiceType("Insert");
		
		System.out.println("Checking if sql is generated properly for insert request...");
			
		String insertSQL = "INSERT INTO \"user_registration\" (\"user_id\",\"age\",\"name\",\"country\",\"city\") VALUES (1,30,'Sabah','Bangladesh','Dhaka')";
		
		JSONObject requestJson = new JSONObject();
		requestJson.put("id", "1");
		requestJson.put("name", "Sabah");
		requestJson.put("age", "30");
		requestJson.put("city", "Dhaka");
		requestJson.put("country", "Bangladesh");		
		
		InsertSQLBuilder insertQuery = new InsertSQLBuilder();		
		assertTrue(insertQuery.getInsertQuery(requestDetail, requestJson).equals(insertSQL));
		
		System.out.println("Insert query properly generated.");
		System.out.println("Done.\n");
	}
	
	@Test
	public void testUpdateQueryBuilder(){
		
		setRequestParam();	
		requestDetail.setServiceType("Update");
		
		System.out.println("Checking if sql is generated properly for update request...");
			
		String updateSQL = "UPDATE \"user_registration\" SET \"age\" = 30,\"name\" = 'Sabah' WHERE \"user_id\" = 1 AND \"age\" > 30";
		
		JSONObject requestJson = new JSONObject();
		requestJson.put("id", "1");
		requestJson.put("name", "Sabah");
		requestJson.put("age", "30");	
		
		UpdateSQLBuilder updateQuery = new UpdateSQLBuilder();		
		assertTrue(updateQuery.getUpdateQuery(requestDetail, requestJson).equals(updateSQL));
		
		System.out.println("Update query properly generated.");
		System.out.println("Done.\n");
	}
	
	@Test
	public void testDeleteQueryBuilder(){
		
		setRequestParam();	
		requestDetail.setServiceType("Delete");
		
		System.out.println("Checking if sql is generated properly for delete request...");
			
		String deleteSQL = "DELETE FROM \"user_registration\" WHERE \"user_id\" = 2 OR \"name\" like 'Sab%'";
		
		JSONObject requestJson = new JSONObject();
		requestJson.put("id", "2");
		requestJson.put("name", "Sab");
		
		DeleteSQLBuilder deleteQuery = new DeleteSQLBuilder();	
		assertTrue(deleteQuery.getDeleteQuery(requestDetail, requestJson).equals(deleteSQL));
		
		System.out.println("Update query properly generated.");
		System.out.println("Done.\n");
	}
}