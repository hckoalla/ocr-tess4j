package core;

import java.util.ResourceBundle;

public class Log  {
	
	public static final void print(String s) {
		print(s, null);
	}

	public static final void print(String s, Exception e) {
		ResourceBundle properties = Util.getProperties();
		
		String DEBUG_MODE = properties.getString( "DEBUG_MODE" );
		if( !Util.isEmptyOrNull( DEBUG_MODE ) ){
			if( Boolean.parseBoolean( DEBUG_MODE ) ){
				System.out.println( "\n"+ s );
				if( e != null ){
					e.printStackTrace();
				}
			}
		}
		
	}
	
}