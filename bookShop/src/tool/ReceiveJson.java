package tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class ReceiveJson {
	public static JSONObject receiveJson( HttpServletRequest request) throws IOException
	{
		String mail="";  
		request.setCharacterEncoding("utf-8");  
	    StringBuffer sb = new StringBuffer();  
	    InputStream is = request.getInputStream();  
	    InputStreamReader isr = new InputStreamReader(is,"utf-8");  
	    BufferedReader br = new BufferedReader(isr);  
	    String s = ""; 
	    while ((s = br.readLine()) != null) {  
	    	sb.append(s);  
	    }
	    String str = sb.toString();
	    mail = s;
	    System.out.println(str + "=========str");
	    
	    JSONObject json = JSONObject.fromObject(mail);
		
		return json;
	}
	
	

}
