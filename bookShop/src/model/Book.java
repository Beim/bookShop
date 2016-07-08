package model;
import tool.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class Book {
	private String bookName;
	private String writer;
	private String publishTime;
	private String publisher;
	private String condition;
	private Integer amount;
	/**
	 * 
	 */
	public Book(){
		bookName	="";
		writer		="";
		publishTime	="";
		publisher	="";
		condition	="";
		amount		=0;
	}
	
	public static JSONObject searchBook(Book book){
		Map data = new HashMap();
		data.put("bookname", book.getBookName());
		data.put("bookId", book.getBookId());
		data.put("publisher", book.getPublisher());
		data.put("writer", book.getWriter());
		JSONObject json = JSONObject.fromObject(data);
		String jsonMessage = HttpRequest.sendGet("http://beim.site:3333/apiv0/book",
				json.toString());
		book.setAmount(Integer.parseInt(JSONObject.fromObject(jsonMessage).getString("amount")));
		
		Map mail = new HashMap();
		
		mail.put("amount", book.getAmount());
		
		JSONObject jsonMail = JSONObject.fromObject(mail);
		
		return jsonMail;
		
	}
	
	public static String buyBook(Book book)
	{
		Map type = new HashMap();
		Map condition = new HashMap();
		condition.put("bookId", book.getBookId());
		Map update = new HashMap();
		update.put("amount", book.getAmount());
		update.put("bookname", book.getBookName());
		Map data = new HashMap();
		data.put("condition", condition);
		data.put("update", update);
		Map mail = new HashMap();
		mail.put("data", data);
		mail.put("type", "put");
//		JSONArray condition = new JSONArray();
//		JSONArray update = new JSONArray();
//		condition.add("bookId", book.getBookId());
//		update.add(0, "dog");
//		jsonStrs.add(2, "bird");
//		jsonStrs.add(3, "duck");
//		
		JSONObject json = JSONObject.fromObject(mail);
		String jsonMessage;
		jsonMessage = HttpRequest.sendPost("http://beim.site:3333/apiv0/book",
				json.toString());
		System.out.println(json.toString());
		return jsonMessage;
		
	}
	public static String addBook(Book book){
		Map data = new HashMap();
		data.put("bookname", book.getBookName());
		data.put("bookId", book.getBookId());
		data.put("amount", book.getAmount());
		Map mail = new HashMap();
		mail.put("data", data);
		mail.put("type", "POST");
		JSONObject json = JSONObject.fromObject(mail);
		String jsonMessage;
			jsonMessage = HttpRequest.sendPost("http://beim.site:3333/apiv0/book",
					json.toString());
		System.out.println(json.toString());
		return jsonMessage; 
	}
	
	private String bookId;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

}
