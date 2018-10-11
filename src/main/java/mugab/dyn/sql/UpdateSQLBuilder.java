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

import java.util.Iterator;

import org.json.JSONObject;

import mugab.handler.json.mapper.JSONMapper;
import mugab.info.service.ServiceDetailBean;

public class UpdateSQLBuilder extends QueryBuilder {

	@SuppressWarnings("unchecked")
	public String getUpdateQuery(ServiceDetailBean requestDetail, JSONObject requestJson){
		
		initialize(requestDetail, "Update");
		
		try{		
			JSONMapper mapper = mapperFactory.getJSONMapper(requestDetail);
			JSONObject detailJson = mapper.getJSONKeyMapper();
					
			String updateSetColumns = "";
			Iterator<String> keys = detailJson.getJSONObject("keyMapField").keys();
				
			while(keys.hasNext()){
				String key = keys.next();
				if(requestJson.has(key)){
					updateSetColumns = updateSetColumns + getPartialQuery(detailJson.getJSONObject("keyMapField"), key, requestJson) + ",";
				}
			}
			
			return "UPDATE " + getTableName(detailJson) 
					+ " SET " 
					+ updateSetColumns.substring(0, updateSetColumns.length()-1) 
					+ " WHERE " 
					+ getConditionString(detailJson, requestJson);
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
}