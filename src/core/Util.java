package core;

import java.util.ResourceBundle;

public class Util {
	
	public static final char BREAK_PAGE_CHARACTER = 12;
	
	public static ResourceBundle getProperties(){
		return ResourceBundle.getBundle( "setup" );
	}
	
	public static boolean isEmptyOrNull(String s){
		return (s==null || s.equals(""));
	}
	
}