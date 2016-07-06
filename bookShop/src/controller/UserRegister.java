package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Code;
import tool.HttpRequest;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserRegister extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserRegister() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		String str = null;
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
	            str = sb.toString();
	            
	            System.out.println("接收到的字符串内容是"+str);  
	        } catch (IOException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        }
		mail = str;
		User user = new User();
		JSONObject jsonMail = JSONObject.fromObject(mail);
//		String username =new String(jsonMail.getString("username"));
//		String password =new String(jsonMail.getString("password"));
		System.out.println(System.getProperty("file.encoding"));
		String u = jsonMail.getString("username");
		System.out.println(Code.encode(u, "gbk", "utf-8"));
		System.out.println(Code.encode(u, "gb2312", "utf-8"));
		System.out.println(Code.encode(u, "iso-8859-1", "utf-8"));
		System.out.println(new String(jsonMail.getString("username").getBytes("utf-8"),"gbk"));
		System.out.println(new String(jsonMail.getString("username").getBytes("utf-8"),"utf-8"));
		System.out.println(new String(jsonMail.getString("username").getBytes("gbk"),"utf-8"));
		System.out.println(new String(jsonMail.getString("username").getBytes("gb2312"),"utf-8"));
		System.out.println(new String(jsonMail.getString("username").getBytes("iso-8859-1"),"utf-8"));
		String username_unicode =new String(jsonMail.getString("username").getBytes("gbk"),"unicode");
		System.out.println(username_unicode);
		String password_unicode =new String(jsonMail.getString("password").getBytes("gbk"),"unicode");
		System.out.println(password_unicode);
		String username =new String(jsonMail.getString("username").getBytes("utf-8"),"utf-8");
		System.out.println(username);
		String password =new String(jsonMail.getString("password").getBytes("utf-8"),"utf-8");
		System.out.println(password);
//		String username =new String(username_unicode.getBytes("iso-8859-1"),"utf-8");
//		String password =new String(password_unicode.getBytes("iso-8859-1"),"utf-8"x);
		System.out.println("注册");
		System.out.println(username);
		System.out.println(password);
		
		user.setUserName(username);
		user.setPassword(password);
		
		System.out.println(password);
		
		String jsonMessage = User.addUser(user);
		
		JSONObject json = JSONObject.fromObject(jsonMessage);
		String success = json.getString("success");
		if (success.equals("true"))
		{
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("password", password);
			request.getSession().setAttribute("loginFlag", "true");
		}		
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
