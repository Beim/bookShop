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

import model.Book;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class buyBook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public buyBook() {
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
	            System.out.println("接收到的字符串内容是"+str);  
	        } catch (IOException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        }
		 JSONObject jsonMail = JSONObject.fromObject(mail);
		 
		 Book book = new Book();
		 
		 book.setBookId(jsonMail.getString("bookId"));
		 book.setBookName(jsonMail.getString("bookname"));
		 book.setAmount(Integer.parseInt(jsonMail.getString("amount")));
		 
		 String result = Book.buyBook(book);
		 
		 JSONObject jsonResult = JSONObject.fromObject(result);
		 
		 Map index = new HashMap();
		 
		 if (jsonResult.getString("success").equals("true"))
		 {
			 index.put("success", "true");
		 }
		 else 
			 index.put("success", "false");
		 
		 JSONArray jsonArray = JSONArray.fromObject(index);
		 
		 response.getWriter().println(jsonArray);
		 
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
	            System.out.println("接收到的字符串内容是"+str);  
	        } catch (IOException e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        }
		 JSONObject jsonMail = JSONObject.fromObject(mail);
		 
		 Book book = new Book();
		 
		 book.setBookId(jsonMail.getString("bookId"));
		 book.setBookName(jsonMail.getString("bookname"));
		 book.setAmount(Integer.parseInt(jsonMail.getString("amount")));
		 
		 String result = Book.buyBook(book);
		 
		 JSONObject jsonResult = JSONObject.fromObject(result);
		 
		 Map index = new HashMap();
		 
		 if (jsonResult.getString("success").equals("true"))
		 {
			 index.put("success", "true");
		 }
		 else 
			 index.put("success", "false");
		 
		 JSONArray jsonArray = JSONArray.fromObject(index);
		 
		 response.getWriter().println(jsonArray);
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
