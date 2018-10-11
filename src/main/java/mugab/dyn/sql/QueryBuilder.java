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

package mugab.dyn.sql;

import mugab.factory.JSONMapperFactory;
import mugab.info.service.ServiceDetailBean;

import org.json.JSONObject;

public class QueryBuilder {
	
	protected JSONMapperFactory mapperFactory = new JSONMapperFactory();
	
	protected void initialize(ServiceDetailBean requestDetail, String operation){
		requestDetail.setServiceType(operation);		
	}
	
	protected String getConditionString(JSONObject condition,JSONObject requestJson){
		String conditionString = "";
		try {
			for(String individualCondition : condition.getString("conditions").split(";")){
				
				if(individualCondition.contains("(")){
					if(individualCondition.contains("BETWEEN") || individualCondition.contains("between")){
						conditionString = conditionString + getPartialQuery (condition.getJSONObject("keyMapCondition"), 
								individualCondition.substring(1,individualCondition.length()-1).split(":")[0], requestJson, 
										individualCondition.substring(1,individualCondition.length()-1).split(":")[1],
										individualCondition.substring(1,individualCondition.length()-1).split(":")[2],
										individualCondition.substring(1,individualCondition.length()-1).split(":")[3]);
					}
					else{
						conditionString = conditionString + getPartialQuery (condition.getJSONObject("keyMapCondition"), 
								individualCondition.substring(1,individualCondition.length()-1).split(":")[0], requestJson, 
										individualCondition.substring(1,individualCondition.length()-1).split(":")[1]);
					}
				}
				else{
					conditionString = conditionString + " " + individualCondition + " ";
				}
			}
			return conditionString;
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	protected String getPartialQuery(JSONObject keyDetail, String key, JSONObject requestParam){
		
		return getColumnName(keyDetail, key) + " = " + getValue(keyDetail, key, requestParam);		
	}

	protected String getPartialQuery(JSONObject keyDetail, String key, JSONObject requestParam, String conditionSign){
		
		return getColumnName(keyDetail, key) + " " + conditionSign + " " + getValue(keyDetail, key, requestParam, conditionSign);		
	}
	
	protected String getPartialQuery(JSONObject keyDetail, String key, JSONObject requestParam, String conditionSign, String val1Key, String val2Key){
		
		return getColumnName(keyDetail, key) + " " + conditionSign + " (" 
					+ getValue(keyDetail, key, requestParam.getString(val1Key)) + ") AND (" 
					+ getValue(keyDetail, key, requestParam.getString(val2Key)) + ")";		
	}
	
	protected String getTableName(JSONObject keyDetail){
		
		return "\"" + keyDetail.getString("table") + "\"";				
	}
	
	protected String getColumnName(JSONObject keyDetail, String key){
		
		return "\"" + keyDetail.getJSONObject(key).getString("dbColumnName") + "\"";				
	}
	
	protected String getValue(JSONObject keyDetail, String key, JSONObject requestDetail){
		return getValue(keyDetail, key, requestDetail, "");
	}
	
	protected String getValue(JSONObject keyDetail, String key, JSONObject requestDetail, String conditionSign){
		
		return (requestDetail.getString(key).equals("") ? 
						keyDetail.getJSONObject(key).getString("dbColumnDefaultValue") : 
						getValue(keyDetail.getJSONObject(key).getString("dbColumnType"),requestDetail.getString(key), conditionSign));				
	}
	
	protected String getValue(JSONObject keyDetail, String key, String val){
		return getValue(keyDetail.getJSONObject(key).getString("dbColumnType"), val);				
	}
	
	protected String getValue(String type, String value){
		return getValue(type, value, "");
	}
	
	protected String getValue(String type, String value, String condition){
		
		switch(type){
			case "int":
				return value;
			case "string":
				if(condition.equals("like")){
					return "'" + value + "%'";
				}
				else{
					return "'" + value + "'";
				}
			case "date":
				return "'" + value + "'";
			default:
				return "";
		}
	}
}