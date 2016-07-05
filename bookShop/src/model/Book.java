package model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
