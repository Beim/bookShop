package tool;

import java.io.UnsupportedEncodingException;

public class Code {
	String str_unicode	= null;
	String str_code2	= null;
	public static String encode(String str, String code1, String code2) throws UnsupportedEncodingException{
		
		String str_unicode	= new String(str.getBytes(code1), "unicode");
		String str_code2	= new String(str.getBytes("unicode"), code2);
		
		return str_code2;
		
	}

}
