
public class Entry {
	
	public static final int STATIC = 0;
	public static final int STRING = 1;
	public static final int LONG = 2;
	public static final int BOTH = 3;
	
	private String name;
	private long phone;
	private int hashType;
	
	public Entry(String name, long phone, int hashType) {
		this.name = name;
		this.phone = phone;
		this.hashType = hashType;
	}
	
	@Override
	public String toString() {
		return name + ":" + phone;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Entry) {
			Entry that = (Entry) other;
			if (that.name.equals(this.name) && that.phone == this.phone)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Returns a hashCode for this object. You should complete the three
	 * different hash functions marked below.
	 * 
	 * Make note that when you write a hash function, it must always return
	 * the same value for the same object. In other words, you should not use
	 * any randomness to generate a hash code.
	 */
	@Override
	public int hashCode() {
		if (hashType == STRING) {
			/* Hash on the String name only. Java has a built-in hashing 
			 * function for Strings; see 
			 * http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/String.html#hashCode%28%29
			 * */
			
						
			//Replace me with your hash function
			return name.hashCode();
		}
		else if (hashType == LONG) {
			/* Hash on the phone number only. You may write whatever hash 
			 * function you like, as long as it involves the phone number 
			 * in some way. Mod and/or division both work well for this. */
			String phone = Long.toString(this.phone);
			    	phone = phone.substring(7);
			//Replace me with your hash function
			int lastThreeNums = Integer.parseInt(phone);
			return lastThreeNums;
		}
		else if (hashType == BOTH) {
			/* Come up with your own hashing function. Your hashing function
			 * should have better performance than each of the other functions 
			 * on at least one of the given input files. 
			 * You may use the name, phone number, or both. */
			String phone = Long.toString(this.phone);
	    	phone = phone.substring(7);
	//Replace me with your hash function
	    	int lastThreeNums = Integer.parseInt(phone);
	    	//lastThreeNums*name.hashCode();
	    	
			//Replace me with your hash function
			return lastThreeNums*name.hashCode()/10;
		}
		else {
			//Fixed hash function
			return 11;
		}
	}
	/**
	 * Sets Entry Name
	 * 
	 */
	public String setName(String name){
		return this.name = name;
	}
	/**
	 * Sets Entry Phone
	 * 
	 */
	public Long setName(Long phone){
		return this.phone = phone;
	}
	/**
	 * Sets hashType
	 * 
	 */
	public int setHashType(int hashType){
		return this.hashType = hashType;
	}
	/**
	 * Gets Entry Name
	 * 
	 */
	public String getName(String name){
		return this.name;
	}
	/**
	 * Gets Entry Phone
	 * 
	 */
	public Long getName(Long phone){
		return this.phone;
	}
	/**
	 * Gets hashType
	 * 
	 */
	public int getHashType(int hashType){
		return this.hashType;
	}
}
