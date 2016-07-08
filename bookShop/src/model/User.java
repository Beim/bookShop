package model;
import tool.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class User {
	private String userName;
	private String password;
	public User(){
		userName = "";
		password = "";
	}
	public static JSONObject validateUser(User user)
	{
		String jsonMessage = HttpRequest.sendGet("http://beim.site:3333/apiv0/user",
				"username="+user.getUserName()+"&password="+user.getPassword());
		String success = "false";
		
		try{
			 JSONObject userJson = JSONObject.fromObject(jsonMessage);
			 success = (userJson.getString("success"));
			
		}catch(Exception e)
		{
			System.out.println("error");
		}
		
		Map mail = new HashMap();
		
		mail.put("success", success);
		
//		mail.put("id", id);
		
		JSONObject jsonMail = JSONObject.fromObject(mail);
		System.out.println(jsonMail);
		return jsonMail;
	}
	public static String addUser(User user)
	{
		

//		try {
//			String gbk_username=new String(user.getUserName().getBytes("ISO-8859-1"));
//			String gbk_password=new String(user.getPassword().getBytes("ISO-8859-1"));
//			String unicode_username = new String(gbk_username.getBytes("unicode"));
//			String unicode_password = new String(gbk_password.getBytes("unicode"));
//			user.setUserName(new String(unicode_username.getBytes("utf-8")));
//			user.setPassword(new String(unicode_password.getBytes("utf-8")));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String gbk= new String(t.getBytes( "GBK"));
//		System.out.println(gbk);
//		String unicode = new String(gbk.getBytes(),"GBK");
//		System.out.println(unicode);
//		String utf8= new String(unicode.getBytes("UTF-8"));
//		System.out.println(utf8);
		Map data = new HashMap();
		data.put("username", user.getUserName());
		data.put("password", user.getPassword());
		Map mail = new HashMap();
		mail.put("data", data);
		mail.put("type", "POST");
		
		JSONObject json = JSONObject.fromObject(mail);
		String jsonMessage = HttpRequest.sendPost("http://beim.site:3333/apiv0/user",
				json.toString());
		System.out.println(json);
		return jsonMessage;
	}
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
