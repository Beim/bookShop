package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.HttpRequest;
import model.Book;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserBuy extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserBuy() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
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
		
		Map params = new HashMap();
		
		params.put("success", "true");
		
		JSONArray jarray = JSONArray.fromObject(params);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
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
		JSONArray jsonMail = JSONArray.fromObject(mail);
//		Object[] obj=jsonMail.toArray();
//		Book book[100] = new Book();
//		Book book[] = new Book[100];
//		for(int i=0;i<obj.length;i++){  
//	        System.out.println(obj[i]);
//	    }
//		while(obj[0].)
//		String bookName[]	= new String[jsonMail.size()];
//		String bookId[]		= new String[jsonMail.size()];
//		String publisher[]	= new String[jsonMail.size()];
//		String writer[]		= new String[jsonMail.size()];
		
		Book books[]			= new Book[jsonMail.size()];
		for (int i = 0; i < jsonMail.size(); i++) {
	   		books[i].setBookName(jsonMail.getJSONObject(i).getString("bookname"));
	   		books[i].setBookId(jsonMail.getJSONObject(i).getString("bookId"));
	   		books[i].setWriter(jsonMail.getJSONObject(i).getString("writer"));
	   		books[i].setPublisher(jsonMail.getJSONObject(i).getString("publisher"));
		}
		
		
		
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
