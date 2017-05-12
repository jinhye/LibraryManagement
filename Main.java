
/**
* @author: JIN HYE CHOI
* @Student ID: 041-162-157
* @Codeboard UserName: jhchoi14
 */
public class Main {

	public static void main(String[] args) {

		/* TASK 1 - build libraries from files - at least two libraries */

		System.out.println("\n\n *" + " TASK 1 " + "*");
		Libraries ls = new Libraries(7);
		
		ls.libraries[0] = ls.buildLibraryFromFile("Downtown", "DowntownLibrary.txt");
		ls.libraries[1] = ls.buildLibraryFromFile("King", "KingLibrary.txt");
		ls.libraries[2] = ls.buildLibraryFromFile("Markham", "MarkhamLibrary.txt");
		ls.libraries[3] = ls.buildLibraryFromFile("Midtown", "MidtownLibrary.txt");
		ls.libraries[4] = ls.buildLibraryFromFile("Newnham", "NewnhamLibrary.txt");
		ls.libraries[5] = ls.buildLibraryFromFile("Uptown", "UptownLibrary.txt");
		ls.libraries[6] = ls.buildLibraryFromFile("Seneca@York", "YorkLibrary.txt");

		
		/* TASK 2 - ask for a book that is not in any library inventory */

		System.out.println("\n\n *" + " TASK 2 " + "*");          
		Book book = new Book("C++", 20);
		Library library = ls.isThereBookInLibraries(book);
		if (library == null)
			System.out.println(Helper.printNonexistent(book));


		/* TASK 3 - ask for a book that is in a library inventory
		 *  issue a rent request and print the bookEssentials of Database Management
		 *  issue the same rent request and print the book
		 *  return the book
		 *  issue the rent request with new dates and print the book
		 */
		System.out.println("\n\n *" + " TASK 3 " + "*");

		String rentDate = Helper.getCurrentDate();
		String dueDate = "04/20/2017";

		Book book1 = new Book("Java Programming Languages",53);
		library = ls.rentBookAvailable(book1, rentDate, dueDate);
		
		if(library!= null){
			if(library.rentRequest(book1, rentDate, dueDate)){
				System.out.println(Helper.printAvailable(book1, rentDate, library));
				book1.rentBook(rentDate, dueDate,library);
				System.out.println("rented = " + book1);
			}else 
				System.out.println(Helper.printUnavailable(book1, rentDate));

			System.out.println("");
			if(library.rentRequest(book1, rentDate, dueDate)){
				System.out.println(Helper.printAvailable(book1, rentDate, library));
				book1.rentBook(rentDate, dueDate,library);
				System.out.println("rented = " + book1);
			}else 
				System.out.println(Helper.printUnavailable(book1, rentDate));

			System.out.println("");
			book1.returnBook(library);
			System.out.println("(" + book1 + ")" + " returned");
			
			System.out.println("");
			if(library.rentRequest(book1, rentDate, dueDate)){
				System.out.println(Helper.printAvailable(book1, rentDate, library));
				book1.rentBook(rentDate, dueDate,library);
				System.out.println("rented = " + book1);
			}else 
				System.out.println(Helper.printUnavailable(book1, rentDate));
		}else     	
			System.out.println(Helper.printUnavailable(book1, rentDate));


		/* TASK 4 - ask for the same book in all libraries
		 * look for the same book in all libraries and return a library, if the book is in the library inventory
		 * look for the same book in all libraries and return a library, if the book is available to be borrowed 
		 * from that library
		 */
		System.out.println("\n\n *" + " TASK 4 " + "*");

		System.out.println("The first Library inventory for " + book1.bookName() + " : " + library.libraryName);

		Library library2 = ls.rentBookAvailable(book1, rentDate, dueDate);
		if(library2!= null){
			if(library2.rentRequest(book1, rentDate, dueDate)){
				//System.out.println(Helper.printAvailable(book4, rentDate, libCheck));
				System.out.println("The first available Library to be rented for " + book1.bookName() + " : " + library2.libraryName);
				//book1.rentBook(rentDate, dueDate,library);
			}else 
				System.out.println(Helper.printUnavailable(book1, rentDate));
		}
		

		/* TASK 5 - calculate maximum value tag for each library */
		System.out.println("\n\n *" + " TASK 5 " + "*");

		for(Library max : ls.libraries){
			System.out.println(max.libraryName + "'s Max value tag: " + max.findMaximumValueTag());
		}
		

		/* TASK 6 - inquire about a book - it is available? when does it become available, etc. */
		/* inquire about a book: is it borrowed?, is it overdue?(if overdue: true) */
		/* could it be found in more than one library? , when can it be borrowed?*/
		System.out.println("\n\n *" + " TASK 6 " + "*");
		
		System.out.println("Book " + book1.bookName() + " in " + book1.library.libraryName + " is rented ?: " + book1.isRented(library));
		System.out.println("Book " + book1.bookName() + " in " + book1.library.libraryName + " is overdue?: " + book1.isBookOverdue());
    	System.out.println("Book " + book1.bookName() + " is found in more than one library: " + (library.books.size() > 1 ? true : false));
		System.out.println("Book " + book1.bookName() + " in " + book1.library.libraryName + " available from: " + book1.availableDate(library));
			
	}	
}
