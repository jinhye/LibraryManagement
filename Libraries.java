import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
/**
* @author: JIN HYE CHOI
* @Student ID: 041-162-157
* @Codeboard UserName: jhchoi14
*/
public class Libraries {

    public Library[] libraries;        // a collection of libraries of type array
    public int numberOfLibraries;      // number of libraries in collection
    
    /**
     * 
     * @param numberOfLibraries
     */
    public Libraries(int numberOfLibraries){
      this.libraries = new Library[numberOfLibraries];
      this.numberOfLibraries = numberOfLibraries;
    }

    /**
     * 
     * @param libraryName
     * @param fileName
     * @return
     */
    public Library buildLibraryFromFile(String libraryName, String fileName) {

        Library library = new Library(libraryName);       
        library.books = new Vector<Book>();
        
        String path = System.getProperty("user.dir");
        Book book = null;
        String s;
        String[] name = null;
         try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        // if you run locally on your environment use: new FileReader(path + "/src/" + fileName)

            while ((s = br.readLine()) != null) {      	
            		name = s.split(",");        	
	            	book = new Book(name[0], Integer.valueOf(name[1]));
	            	library.books.add(book);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }      
         
        System.out.println("Library = " + libraryName);
        System.out.println("[");
        System.out.print(library);
        System.out.println("]");
        

        return library;    
    }
    
    /**
     * 
     * @param book
     * @return
     */
    public Library isThereBookInLibraries(Book book) {    
      for(int i =0; i<libraries.length; i++){
    	  for(Book b : libraries[i].books){
    		  if(b.bookName.equals(book.bookName)){
    			 return libraries[i];
    		  }
    	  }
      }
       return null;
    }
    
    /**
     * 
     * @param book
     * @param requestDate
     * @param dueDate
     * @return
     */
    public Library rentBookAvailable(Book book, String requestDate, String dueDate) {

        Library foundLibrary = null;
        
        for(int i=0; i <  this.numberOfLibraries; i++){       	
        	for(Book o :libraries[i].books){
        		//if(book.equals(o))
        		if(book.equals(o) && !(libraries[i].books.elementAt(libraries[i].books.indexOf(o)).isRented(libraries[i])) )
        		{
        			return libraries[i];
        		}        		
        	}        		
        }         
        return foundLibrary;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(libraries);
		result = prime * result + numberOfLibraries;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Libraries))
			return false;
		Libraries other = (Libraries) obj;
		if (!Arrays.equals(libraries, other.libraries))
			return false;
		if (numberOfLibraries != other.numberOfLibraries)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "";
	}    
    
}
