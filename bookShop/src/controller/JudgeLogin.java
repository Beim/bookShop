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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JudgeLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JudgeLogin() {
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
		HttpSession session = request.getSession();
		String flag = null;
		//flag = session.getAttribute("loginFlag").toString();
		if (session.getAttribute("username")!=null)
		{
			Map data = new HashMap();
			data.put("success", "true");
			data.put("username", session.getAttribute("username"));
			data.put("password", session.getAttribute("password"));
			JSONArray json = JSONArray.fromObject(data);
			System.out.println(json);
			response.getWriter().println(json);
			System.out.println("成功");
		}
		else
		{
			Map data = new HashMap();
			data.put("success", "false");
			data.put("username", null);
			data.put("password", null);
			JSONArray json = JSONArray.fromObject(data);
			System.out.println(json);
			response.getWriter().println(json);
			System.out.println("失败");
		}
			
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
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String mail = null;
//		try {  
//		 	request.setCharacterEncoding("utf-8");  
//            StringBuffer sb = new StringBuffer();  
//            InputStream is = request.getInputStream();  
//            InputStreamReader isr = new InputStreamReader(is,"utf-8");  
//            BufferedReader br = new BufferedReader(isr);  
//            String s = "";  
//            while ((s = br.readLine()) != null) {  
//                sb.append(s);  
//            }  
//            String str = sb.toString();
//            mail = str;
//            System.out.println("接收到的字符串内容是"+str);  
//        } catch (IOException e1) {  
//            // TODO Auto-generated catch block  
//            e1.printStackTrace();  
//        }
//		String id = mail;
		String flag = null;
//		System.out.println(id);
		HttpSession session = request.getSession();
//		HttpSessionContext SessCon= request.getSession(false).getSessionContext();
//		HttpSession session1 = SessCon.getSession(id); 
		//flag = session.getAttribute("loginFlag").toString();
		if (session.getAttribute("username")!=null)
		{
			Map success = new HashMap();
			Map username = new HashMap();
			Map password = new HashMap();
			success.put("success", "true");
			username.put("username", session.getAttribute("username"));
			password.put("password", session.getAttribute("password"));
			JSONArray json = new JSONArray();
			json.add(0, success);
			json.add(1, username);
			json.add(2, password);
			System.out.println(json);
			response.getWriter().println(json);
			System.out.println("成功");
		}
		else
		{
			Map data = new HashMap();
			data.put("success", "false");
			data.put("username", null);
			data.put("password", null);
			JSONArray json = JSONArray.fromObject(data);
			System.out.println(json);
			response.getWriter().println(json);
			System.out.println("失败");
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
