import java.util.Iterator;
import java.util.Vector;

/**
* @author: JIN HYE CHOI
* @Student ID: 041-162-157
* @Codeboard UserName: jhchoi14
 */
public class Library implements MaxTagValue {

    String       libraryName;
    Vector<Book> books;
    
    /**
     * 
     * @param libraryName
     */
    public Library(String libraryName) {    
    	this.libraryName = libraryName;
    	books = new Vector<Book>();
    }
    //this method search all the books from a library
    //and returns an integer that is the maximum value tag
    public int findMaximumValueTag() {    	
        int maxElement = -100;        
        for(Book bb : books){
        	if(bb.valueTag > maxElement){
        		maxElement = bb.valueTag;
        	}
        }        
        return maxElement;
    }
    
    //the method takes three params: a book and two dates
    //the method returns true if the book is available in this library
    //from requestData up to dueDate
    /**
     * 
     * @param wanted
     * @param requestDate
     * @param dueDate
     * @return
     */
    public boolean rentRequest(Book wanted, String requestDate, String dueDate) {   	
    	try {
            Helper.checkDate(requestDate);
            Helper.checkDate(dueDate);
            Helper.timeDifference(requestDate, dueDate);   
            
            for(Book o : this.books)
            {
            	if(o.equals(wanted) && !(this.books.elementAt(this.books.indexOf(o)).isRented(this)) ){
            		return true;
            	}            	
            }
            return false;
        } catch (DateFormatException e) {
            System.out.println(wanted + e.getMessage());
            return false;
        }    
    }  	  

	@Override
	public String toString() {
		String r = "";
		for(Book book : books){
			r += "(" + book.toString() +  " => " + libraryName + ")" + book.getRs() + "\n";
		}
		return r;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((libraryName == null) ? 0 : libraryName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Library))
			return false;
		Library other = (Library) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (libraryName == null) {
			if (other.libraryName != null)
				return false;
		} else if (!libraryName.equals(other.libraryName))
			return false;
		return true;
	}    
    
}
