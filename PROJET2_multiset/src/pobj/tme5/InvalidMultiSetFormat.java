package pobj.tme5;

public class InvalidMultiSetFormat extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMultiSetFormat(String msg){
		System.out.println(msg);
	}
	
	public InvalidMultiSetFormat(String msg, Throwable cause) {
		System.out.println(msg + cause);
		
	}

	

}
