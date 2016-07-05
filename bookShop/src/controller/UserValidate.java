package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import model.HttpRequest;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class UserValidate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserValidate() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		//mail.put(type, )
		user.setUserName("hebly723");
		user.setPassword("comming");
		String jsonMessage = User.addUser(user);
		//String jsonMessage = HttpRequest.sendPost("http://localhost:3333/apiv0/user",
			//	json.toString());
		System.out.println(jsonMessage);
//
//		String mail="";
//		 try {  
//			 	request.setCharacterEncoding("utf-8");  
//	            StringBuffer sb = new StringBuffer();  
//	            InputStream is = request.getInputStream();  
//	            InputStreamReader isr = new InputStreamReader(is,"utf-8");  
//	            BufferedReader br = new BufferedReader(isr);  
//	            String s = "";  
//	            while ((s = br.readLine()) != null) {  
//	                sb.append(s);  
//	            }  
//	            String str = sb.toString();
//	            mail = s;
//	            System.out.println(str + "=========str");  
//	        } catch (IOException e1) {  
//	            // TODO Auto-generated catch block  
//	            e1.printStackTrace();  
//	        }
//		User user = new User();
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String success = "false";
//		//'http://beim.site:3333/apiv0/user?' + queryStr
//		String jsonMessage = HttpRequest.sendGet("http://beim.site:3333/apiv0/user",
//				"username="+username+"&password="+password);
//		try{
//			 JSONObject userJson = JSONObject.fromObject(jsonMessage);
//			 success = (userJson.getString("success"));
//			
//		}catch(Exception e)
//		{
//			System.out.println("error");
//		}
//		
//		Map params = new HashMap();
//		
//		params.put("success", "true");
//		
//		JSONArray jarray = JSONArray.fromObject(params);
//		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println(jarray);
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("username:"+username);
//		out.println("password:"+password);
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the POST method");
//		if (success.equals("true"))
//			out.println("<br/>"+"Congratulation for login successfully!");
//		else
//			out.println("<br/>"+"unfortunately you are lose.");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail="";
		 try {  
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
	            mail = str;
	            System.out.println(str + "=========str");  
	        } catch (IOException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        }
		User user = new User();
		JSONObject jsonMail = JSONObject.fromObject(mail);
		String username = jsonMail.getString("username");
		
		String password = jsonMail.getString("password");
		System.out.println(username);
		System.out.println(password);
		
		String success = "false";
		//'http://beim.site:3333/apiv0/user?' + queryStr
		String jsonMessage = HttpRequest.sendGet("http://beim.site:3333/apiv0/user",
				"username="+username+"&password="+password);
		try{
			 JSONObject userJson = JSONObject.fromObject(jsonMessage);
			 success = (userJson.getString("success"));
			
		}catch(Exception e)
		{
			System.out.println("error");
		}
		
		
		
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("username:"+username);
//		out.println("password:"+password);
//		out.println("  <BODY>");
//		out.println("    This is ");
//		out.println(this.getClass());
//		out.println(", using the POST method");
		Map params = new HashMap();
		if (success.equals("true"))
			//out.println("<br/>"+"Congratulation for login successfully!");
			params.put("success", "true");
		else
			//out.println("<br/>"+"unfortunately you are lose.");
			params.put("success", "false");
//		out.println("  </BODY>");
//		out.println("</HTML>");
		
		JSONArray json = JSONArray.fromObject(params);
		
		String jsonString = json.toString();
		
		//String Message1 = HttpRequest.sendPost("http://beim.site:3333/apiv0/user",
			//	jsonString);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		//response.addHeader(name, value);
		
		System.out.println(json);
		response.getWriter().print(json);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
