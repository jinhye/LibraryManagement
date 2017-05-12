/**
* @author: JIN HYE CHOI
* @Student ID: 041-162-157
* @Codeboard UserName: jhchoi14
*/
class Book {

    String       bookName;    // the book name
    int          valueTag;    // an integer between -100 and 100
    Library      library;     // the library having this book it its inventory
    RentSettings rs;          // rent settings
    /////
    String date;
    
    /** 
     * @param bookName
     * @param valueTag
     */
    public Book(String bookName, int valueTag) {
    	this.bookName = bookName;
    	this.valueTag = valueTag;
    	this.library = null;
    	this.rs = null;
    	try {
			this.rs = new RentSettings();
		} catch (DateFormatException e) {
			e.printStackTrace();
		}
    }

    // set the rent dates; if dates are not valid catch DateFormatException and return false,
    // if rentDate > dueDate catch RentPeriodException and return false
    // if one the exceptions occur there is no RentSettings object
    /**
     * @param rentDate
     * @param dueDate
     * @param library
     * @return
     */
    public boolean rentBook(String rentDate, String dueDate, Library library) {	
        RentSettings rs = null;
        try{
        	Helper.checkDate(rentDate);
        	Helper.checkDate(dueDate);
        	if(Helper.timeDifference(rentDate,  dueDate) < 0){
        		throw new RentPeriodException(Helper.printNonexistent(this));
        	}
        	
        	rs = new RentSettings(rentDate, dueDate, library);        	
    
        	date = rs.getDueDate();
        	for(Book o :library.books){
        		if(this.equals(o) && !(library.books.elementAt(library.books.indexOf(o)).isRented(library))){
        			library.books.elementAt(library.books.indexOf(o)).setRs(rs);
        			return true;
        		}
        	}
        }catch(DateFormatException e){
        	e.printStackTrace();
        	this.rs = null;
        	return false;
        }catch(RentPeriodException e){
        	e.printStackTrace();
        	this.rs = null;
        	return false;
        }   
        return false;
    }

    // destroy the RentSettings object for this book
    /**
     * @param library
     */
    public void returnBook(Library library) {    	
    	for(Book o :library.books){
    		if(o.equals(this)){
    			library.books.elementAt(library.books.indexOf(o)).setRs(null);
    		}
    	}
    }
      
    // return the date when this book is available
    /**
     * @param library
     * @return
     */
    public String availableDate(Library library) {
    	if(this.isRented(library))
    		return date;
    	else
    		return Helper.getCurrentDate();
    }

    // returns true if the current date is greater than the due date
    /**
     * @return
     */
    public boolean isBookOverdue() {
    	try {
			if(Helper.timeDifference(Helper.getCurrentDate(), date) <= 0){
				return true;
			}
		} catch (DateFormatException e) {
			e.printStackTrace();
		}
        return false;
    }
    
    /**
     * @param l
     * @return
     */
    public boolean isRented(Library l) {
    	if(l != null){
    		for(Book o:l.books){
    			try{
    				if(this.equals(o) && l.books.elementAt(l.books.indexOf(o)).rs.borrowed)
    					return true;
	    		}catch(Exception e){
    				e.getMessage();
	    		}
    		}
    	}
       return false;
    }

    /**
     * @return
     */
    public RentSettings getRs() {
       //RentSettings rs = null;     
       return rs;
    }

    /** 
     * @param rs
     */
    public void setRs(RentSettings rs) {
    	this.rs = rs;
    }

	@Override
    public String toString() {
    	String s = bookName + ", " + valueTag;     
        return s;
    }

	/**
	 * @return
	 */
    public String bookName() {
    	return "(" + bookName + ", " + valueTag + ")";
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rs == null) ? 0 : rs.hashCode());
		result = prime * result + valueTag;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if(obj instanceof Book)
		{
			Book other =(Book)obj;
			return (other.bookName.equals(this.bookName))&&(this.valueTag == other.valueTag);
		}else{
			return false;			
		}
	}

	/**
	 * @author JIN HYE CHOI
	 */
    private class RentSettings {

        private String rentDate;          // date when the item is requested
        private String dueDate;           // date when the item must be returned
        private boolean borrowed = false; // true if the item is rented

        //default ctr
        private RentSettings() throws DateFormatException {
        	this.rentDate = "";
        	this.dueDate = "";
        }

        // private ctr must throw DateFormatException and RentPeriodException
        private RentSettings(String rentDate, String dueDate, Library library)
                throws DateFormatException, RentPeriodException {
        	this.rentDate = rentDate;
        	this.dueDate = dueDate;
        	Book.this.library = library;   
        	this.borrowed = true;
        }

        @Override
        public String toString() {
            return " RentSettings (" + rentDate +", " + dueDate + ", borrow= " + borrowed + ")";
        }
        
        public String getDueDate(){
        	return this.dueDate;
        }      
    }
}
