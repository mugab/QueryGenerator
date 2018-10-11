package mugab.example;

import java.util.Properties;

public class ServiceJSONMapProperties {
	
	public static Properties getServiceJSONMap(){
		
		Properties serviceJSONMap = new Properties();
		serviceJSONMap.setProperty("Registration|id", "user_id;int;null;null");
		serviceJSONMap.setProperty("Registration|name", "name;string;null;null");
		serviceJSONMap.setProperty("Registration|age", "age;int;null;null");
		serviceJSONMap.setProperty("Registration|city", "city;string;null;null");
		serviceJSONMap.setProperty("Registration|country", "country;string;null;null");
		
		return serviceJSONMap;
	}
}