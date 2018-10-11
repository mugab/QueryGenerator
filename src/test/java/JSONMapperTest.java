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
import mugab.factory.JSONMapperFactory;
import mugab.info.service.ServiceDetailBean;

/**
 * Description: This class is to do the unit testing of the application functionality. 
 **/

public class JSONMapperTest {

	JSONMapperFactory mapperFactory = new JSONMapperFactory();
	ServiceDetailBean requestDetail = new ServiceDetailBean();
	
	private void setRequestParam(){
		requestDetail.setServiceName("Registration");
		requestDetail.setServiceJsonProperties(TestProperties.getServiceJSONProperties());
		requestDetail.setServiceJsonMapProperties(TestProperties.getServiceJSONMapProperties());
	}
	
	@Test
	public void testRetieveJSONKeyMapper(){
		
		setRequestParam();
		requestDetail.setServiceType("Retrieve");
		
		System.out.println("Checking if json map is generated properly for retrieve request...");
		
		JSONObject detailJson = mapperFactory.getJSONMapper(requestDetail).getJSONKeyMapper();
		
		String[] columnJSONKeys = {"id","name","city","country"};
		String[] conditionColumnJSONKeys = {"id","age"};
		
		for(String key : columnJSONKeys){
			assertTrue(detailJson.getJSONObject("keyMapField").has(key));			
		}
		System.out.println("All DB column details exist for Retrieve operation.");
		
		for(String key : conditionColumnJSONKeys){
			assertTrue(detailJson.getJSONObject("keyMapCondition").has(key));			
		}
		System.out.println("All DB column details exist for Retrieve operation (condition).");
		System.out.println("Done.\n");
	}
	
	@Test
	public void testInsertJSONKeyMapper(){
		
		setRequestParam();
		requestDetail.setServiceType("Insert");
		
		System.out.println("Checking if json map is generated properly for insert request...");
		
		JSONObject detailJson = mapperFactory.getJSONMapper(requestDetail).getJSONKeyMapper();
		
		String[] columnJSONKeys = {"id","name","age"};
		
		for(String key : columnJSONKeys){
			assertTrue(detailJson.getJSONObject("keyMapField").has(key));			
		}
		
		System.out.println("All DB column details exist for Insert operation.");
		System.out.println("Done.\n");
	}
	
	@Test
	public void testUpdateJSONKeyMapper(){
		
		setRequestParam();
		requestDetail.setServiceType("Update");
		
		System.out.println("Checking if json map is generated properly for update request...");
		
		JSONObject detailJson = mapperFactory.getJSONMapper(requestDetail).getJSONKeyMapper();
		
		String[] columnJSONKeys = {"name","age"};
		String[] conditionColumnJSONKeys = {"id","age"};
		
		for(String key : columnJSONKeys){
			assertTrue(detailJson.getJSONObject("keyMapField").has(key));			
		}
		System.out.println("All DB column details exist for Update operation.");
		
		for(String key : conditionColumnJSONKeys){
			assertTrue(detailJson.getJSONObject("keyMapCondition").has(key));			
		}
		System.out.println("All DB column details exist for Update operation (condition).");
		System.out.println("Done.\n");
	}
	
	@Test
	public void testDeleteJSONKeyMapper(){
		
		setRequestParam();
		requestDetail.setServiceType("Delete");
		
		System.out.println("Checking if json map is generated properly for delete request...");
		
		JSONObject detailJson = mapperFactory.getJSONMapper(requestDetail).getJSONKeyMapper();
				
		String[] conditionColumnJSONKeys = {"id","name"};
		
		for(String key : conditionColumnJSONKeys){
			assertTrue(detailJson.getJSONObject("keyMapCondition").has(key));			
		}
		System.out.println("All DB column details exist for Delete operation (condition).");
		System.out.println("Done.\n");
	}
	
}