import java.util.Properties;

public class TestProperties {
	
	public static Properties getServiceJSONMapProperties(){
		
		Properties serviceProperties = new Properties();
		
		serviceProperties.setProperty("Registration|id", "user_id;int;null;null");
		serviceProperties.setProperty("Registration|name", "name;string;null;null");
		serviceProperties.setProperty("Registration|age", "age;int;null;null");
		serviceProperties.setProperty("Registration|city", "city;string;null;null");
		serviceProperties.setProperty("Registration|country", "country;string;null;null");
				
		return serviceProperties;
	}
	
	
	public static Properties getServiceJSONProperties(){
		
		Properties serviceJSONProperties = new Properties();
		
		serviceJSONProperties.setProperty("Registration|table", "user_registration");
		//Retrieve JSON
		//serviceJSONProperties.setProperty("Registration|Retrieve|json", "all");
		//serviceJSONProperties.setProperty("Registration|Retrieve|condition", "(id:=);OR;(age:between:startage:endage)"); //startage and endage are only for values. This is only for BETWEEN/*Special case*/
		//Retrieve JSON
		serviceJSONProperties.setProperty("Registration|Retrieve|json", "id;name;city;country");
		serviceJSONProperties.setProperty("Registration|Retrieve|condition", "(id:=);AND;(age:>=)");		
		//Insert JSON
		serviceJSONProperties.setProperty("Registration|Insert|json", "id;name;age;city;country");
		serviceJSONProperties.setProperty("Registration|Insert|condition", "");
		//Update JSON
		serviceJSONProperties.setProperty("Registration|Update|json", "name;age");
		serviceJSONProperties.setProperty("Registration|Update|condition", "(id:=);AND;(age:>)");
		//serviceJSON.setProperty("Registration|Update|condition", "(id:=)");
		//Delete JSON
		serviceJSONProperties.setProperty("Registration|Delete|json", "");
		serviceJSONProperties.setProperty("Registration|Delete|condition", "(id:=);OR;(name:like)");
		
		return serviceJSONProperties;
	}	
}