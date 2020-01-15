/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author Michael
 */
public class BookCollection {

    private final int MAXBOOKS = 30000;
    private Book[] bookList = new Book[MAXBOOKS];   
    
    public BookCollection() {
    // code not included ...
    }
    
    public int countScienceBooks(String cNum){
        
        int i = 0, count = 0;
        
        while(this.bookList[i] != null){
            if(bookList[i].getCallNumber().equals(cNum)){
                count++;
            }
            i++;
        }
        return count;
    }
    
    public static void main(String[] args){
        BookCollection books = new BookCollection();
        
        books.bookList[0] = new Book("Special Relativity","A. Einstein","Q");
        
        System.out.println(books.bookList[200] == null);
        
        books.bookList[1] = new Book("Integers and Math","C. Yang","Z");
        books.bookList[2] = new Book("To Kill A Mocking Bird","H. Lee","V");
        books.bookList[3] = new Book("Something Something","N. Body","Q");
        books.bookList[4] = new Book("Path Integrals","R. Feynman","Q"); 
        
        String cNum = "Q";
        System.out.println(books.countScienceBooks(cNum));
    } 
}
