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

package mugab.handler.json;

import mugab.info.service.ServiceDetailBean;
import org.json.JSONObject;

public class Mapper {

	protected JSONObject keyMapper;
	
	protected void initialize(ServiceDetailBean requestDetail){
		
		keyMapper = new JSONObject();
		
		keyMapper.put("table", requestDetail.getServiceJsonProperties().getProperty(requestDetail.getServiceName() + "|table"));
		keyMapper.put("conditions", requestDetail.getServiceJsonProperties().getProperty(requestDetail.getServiceName() + "|" + requestDetail.getServiceType() + "|condition"));
		keyMapper.put("fields", requestDetail.getServiceJsonProperties().getProperty(requestDetail.getServiceName() + "|" + requestDetail.getServiceType() + "|json"));
	}
	
	protected void setInnerKeys(ServiceDetailBean requestDetail, JSONObject mapper, String keyDetail){
		
		JSONObject keyMap = new JSONObject();
				
		for(String keyName : mapper.getString(keyDetail).split(";")){
			if(keyDetail.equals("conditions")){
				if(keyName.contains("(")){
					keyName = keyName.split("\\(")[1].split(":")[0];
				}
				else{
					continue;
				}
			}
			keyMap.put(keyName, getDBDetail(requestDetail, keyName));
		}
		mapper.put(keyDetail.equals("fields") ? "keyMapField" : "keyMapCondition", keyMap);
	}
	
	private JSONObject getDBDetail(ServiceDetailBean requestDetail, String keyName){
		
		JSONObject innerKey = new JSONObject();
		
		if(requestDetail.getServiceJsonMapProperties().getProperty(requestDetail.getServiceName() + "|" + keyName) != null){
			String[] dbInfo = requestDetail.getServiceJsonMapProperties().getProperty(requestDetail.getServiceName() + "|" + keyName).split(";");
			
			innerKey.put("dbColumnName", dbInfo[0]);
			innerKey.put("dbColumnType", dbInfo[1]);
			innerKey.put("dbColumnDefaultValue", dbInfo[2]);
			innerKey.put("responseDefaultValue", dbInfo[3]);
		}		
		return innerKey;
	}
}