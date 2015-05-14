import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * This class implements a hashtable that using chaining for collision handling.
 * The chains are implemented using LinkedLists.  When a hashtable is created, 
 * its initial size and maximum load factor are specified. The hashtable can 
 * hold arbitrarily many items and resizes itself whenever it reaches 
 * its maximum load factor. Note that this hashtable allows duplicate entries.
 */
public class HashTable<T> {
	/* Add private variables here as needed */
	
	private int tableSize;
	private double maxLoadFactor;
	//private double currLoadFactor;
	private int numItems;
	private LinkedList<T>[] hashArray;
	private LinkedList<T> allItems;
	
	
    /**
     * Constructs an empty hashtable with the given initial size and maximum '
     * load factor. The load factor should be a real 
     * number greater than 0.0 (not a percentage).  For example, to create a 
     * hash table with an initial size of 10 and a load factor of 0.85, one 
     * would use:
     * <dir><tt>HashTable ht = new HashTable(10, 0.85);</tt></dir>
     *
     * @param initSize The initial size of the hashtable.  If the size is less
     * than or equal to 0, an IllegalArgumentException is thrown.
     * @param loadFactor The load factor expressed as a real number.  If the
     * load factor is less than or equal to 0.0, an IllegalArgumentException is
     * thrown.
     **/
    public HashTable(int initSize, double loadFactor)throws IllegalArgumentException {
    	
    		if(initSize <=0 || loadFactor <= 0.0 ){
    			throw new IllegalArgumentException();
    		}
    		/**
    		if( initSize == null || loadFactor == null){
    			throw new NullPointerException();
    		}
    		
    	*/
    		this.tableSize = initSize;
    		this.maxLoadFactor = loadFactor;
    		//this.hashArray = (LinkedList<T>[]) new Object [initSize];
    		this.hashArray = (LinkedList<T>[])  new LinkedList[initSize];
    		for(int i=0; i<hashArray.length; i++){
    			hashArray[i] = new LinkedList<T>();
    		}
    		this.numItems = 0;
    		allItems = new LinkedList<T>();
    }
    
    
    /**
     * Determines if the given item is in the hashtable and returns it if 
     * present.  If more than one copy of the item is in the hashtable, the 
     * first copy encountered is returned.
     *
     * @param item the item to search for in the hashtable
     * @return the item if it is found and null if not found
     **/
    public T lookup(T item) {
    	
    	if(item == null){
    		throw new NullPointerException();
    	}
    	
 
    	
    	int code = hashFunction(item.hashCode());
    	
    	boolean found = false;
    	
    	if( hashArray[code].contains(item) ){
    		
    		found = true;
    	}
    	
    	if(found){
    		return item;
    	}else{
    		return null;
    	}
 
    	
    	
    	
    	/**
    	if( hashArray[code].size() > 1 ){
    		
    		for(int i = 0; i < hashArray[code].size(); i++){
    			
    			if( hashArray[code].get(i).equals(item)){
    				
    				return hashArray[code].get(i);
    			}
    		}
    		
    	}else if(hashArray[code].size() == 1 ){
    		
    		return hashArray[code].get(0);
    	}
    	else{
    		return null;
    	}
    	 */  	
    	
    }
    
    
    /**
     * Inserts the given item into the hash table.  
     * 
     * If the load factor of the hashtable after the insert would exceed 
     * (not equal) the maximum load factor (given in the constructor), then the 
     * hashtable is resized.
     * 
     * When resizing, to make sure the size of the table is good, the new size 
     * is always 2 x <i>old size</i> + 1.  For example, size 101 would become 
     * 203.  (This  guarantees that it will be an odd size.)
     * 
     * <p>Note that duplicates <b>are</b> allowed.</p>
     *
     * @param item the item to add to the hashtable
     **/
    public void insert(T item) {
    	
    	
    	if(item == null){
    		throw new NullPointerException("from insert function!!");
    	}
    	
    	//System.out.println(	"HERE" +item.toString());
    //	allItems.add(item);
    	
    	hashArray[hashFunction(item.hashCode())].add(item);
    	numItems++;
    	
    	if( (numItems)/tableSize >= maxLoadFactor){
    		
    //	Object[] obh = new Object [ (tableSize * 2) + 1 ];
    //	LinkedList<T>[] temp = Arrays.copyOf(obh, obh.length, LinkedList[].class);
    		
    		
    	LinkedList<T>[] temp = (LinkedList<T>[]) new LinkedList [ (tableSize * 2) + 1 ];
    	tableSize =  (tableSize * 2) + 1 ;
    	for(int i=0; i<temp.length; i++){
			temp[i] = new LinkedList<T>();
		}
   
    	for(int i = 0; i < hashArray.length; i++){
    		//if(hashArray[i] != null){
    		for( T thing: hashArray[i]){
    				temp[hashFunction(thing.hashCode())].add(thing);
    			}
    		//}
    	}
    	
    	/**
    	for( T thing: allItems ){
    			temp[hashFunction(thing.hashCode())].add(item);
    	}
    	*/
    		hashArray = temp;
    	
    		
    	}
    	
    	
    }
    
    
    /**
     * Removes and returns the given item from the hashtable.  If the item is 
     * not in the hashtable, <tt>null</tt> is returned.  If more than one copy 
     * of the item is in the hashtable, only the first copy encountered is 
     * removed and returned.
     *
     * @param item the item to delete in the hashtable
     * @return the removed item if it was found and null if not found
     **/
    public T delete(T item) {
    	
    	if(item == null){
    		throw new NullPointerException();
    	}
    	
    	int code = hashFunction(item.hashCode());
    	boolean removed= false;
    	
    	if( hashArray[code].contains(item) ){
    		
    		removed = hashArray[code].remove(item);
    	}
    	
    	if(removed){
    	//	allItems.remove(item);
    		numItems--;
    		return item;
    	}else{
    		return null;
    	}
 
    	/**
    	int code = hashFunction(item.hashCode());
    	
    	if( hashArray[code].size() > 1 ){
    		
    		for(int i = 0; i < hashArray[code].size(); i++){
    			
    			if( hashArray[code].get(i).equals(item)){
    				numItems--;
    				
    				return hashArray[code].remove(i);
    				
    			}
    		}
    		
    	}else if(hashArray[code].size() == 1 ){
    		numItems--;
    		
    		return hashArray[code].remove(0);
    	
    	}	
    	else{
    		return null;
    	}
    */	
    }
    
  
    /**
     * Prints statistics about the hashtable to the PrintStream supplied.
     * The statistics displayed are: 
     * <ul>
     * <li>the current table size
     * <li>the number of items currently in the table 
     * <li>the current load factor
     * <li>the length of the largest chain
     * <li>the number of chains of length 0
     * <li>the average length of the chains of length > 0
     * </ul>
     *
     * @param out the place to print all the output
     **/
    public void displayStats(PrintStream out) {
        out.println("Table Size: "+ tableSize);
        out.println("Number of Items: " + numItems);
        out.println("Load Factor: " +( (double) numItems)/tableSize);
        
        int countZeroChains=0;
        int sumChainLength=0;
        int numNonZeroChains = 0;
        int largestChain=0;
        
        for(int i=0; i < tableSize; i++){
        	
        	if(hashArray[i].size() == 0){
        		countZeroChains++;
        	}
        	else if(hashArray[i].size() > 0){
        		sumChainLength+=hashArray[i].size();
        		numNonZeroChains++;
        		if(largestChain < hashArray[i].size()){
        			largestChain = hashArray[i].size();
        		}
        	}
        }
        out.println("Longest Chain: "+ largestChain);
        out.println("Number of Length 0 Chains: "+ countZeroChains);
        out.println("Average Chain Length: " + ( (double) sumChainLength)/numNonZeroChains);
       
        
    }
    /**
     * Hash Function
     */
    private int hashFunction(int hashCode){
    	
    	int hashTableIndex = hashCode%tableSize;
    	    
    	return Math.abs(hashTableIndex);
    }
}
