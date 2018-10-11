package mugab.example;

import java.util.Properties;

public class ServiceJSONProperties {
	
	public static Properties getServiceJSON(){
		
		Properties serviceJSON = new Properties();
		
		serviceJSON.setProperty("Registration|table", "user_registration");
		//Retrieve JSON
		//serviceJSON.setProperty("Registration|Retrieve|json", "all");
		//serviceJSON.setProperty("Registration|Retrieve|condition", "(id:=);OR;(age:between:startage:endage)"); //startage and endage are only for values. This is only for BETWEEN/*Special case*/
		//Retrieve JSON
		serviceJSON.setProperty("Registration|Retrieve|json", "id;name;city;country");
		serviceJSON.setProperty("Registration|Retrieve|condition", "(id:=);AND;(age:>=)");		
		//Insert JSON
		serviceJSON.setProperty("Registration|Insert|json", "id;name;age;city;country");
		serviceJSON.setProperty("Registration|Insert|condition", "");
		//Update JSON
		serviceJSON.setProperty("Registration|Update|json", "name;age");
		serviceJSON.setProperty("Registration|Update|condition", "(id:=);AND;(age:>)");
		//serviceJSON.setProperty("Registration|Update|condition", "(id:=)");
		//Delete JSON
		serviceJSON.setProperty("Registration|Delete|json", "");
		serviceJSON.setProperty("Registration|Delete|condition", "(id:=);OR;(name:like)");
		
		
		return serviceJSON;
	}
}
