package model;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

=======
>>>>>>> f9150fbd6846b33ccb35d2ce00fb7bb04d99ecf1
public class User {
	private String userName;
	private String password;
	public User(){
		userName = "";
		password = "";
	}
<<<<<<< HEAD
	public static String addUser(User user)
	{
		Map data = new HashMap();
		data.put("username", user.getUserName());
		data.put("password", user.getPassword());
		Map mail = new HashMap();
		mail.put("data", data);
		mail.put("type", "POST");
		JSONObject json = JSONObject.fromObject(mail);
		String jsonMessage = HttpRequest.sendPost("http://beim.site:3333/apiv0/user",
				json.toString());
		return jsonMessage;
	}
=======
>>>>>>> f9150fbd6846b33ccb35d2ce00fb7bb04d99ecf1
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
