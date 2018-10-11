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

public class JSONKeyMapper extends Mapper {
	
	public JSONKeyMapper(ServiceDetailBean requestDetail){
		setKeyMapper(requestDetail);					
	}
	
	private void setKeyMapper(ServiceDetailBean requestDetail){
		initialize(requestDetail);
		setKeyMap(requestDetail, keyMapper);
		setConditionKeyMap(requestDetail, keyMapper);
	}
	
	public JSONObject getKeyMapper(){
		return keyMapper;
	}
	
	private void setKeyMap(ServiceDetailBean requestDetail, JSONObject keyMapper){
		setInnerKeys(requestDetail, keyMapper, "fields");
	}
	
	private void setConditionKeyMap(ServiceDetailBean requestDetail, JSONObject keyMapper){
		setInnerKeys(requestDetail, keyMapper, "conditions");
	}	
}