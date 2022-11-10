package com.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        System.out.println("are you running");
        book.setId(id);
        id++;
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()

    @GetMapping("/get-book-by-id/{id}")
    ResponseEntity<Book> getBookById(@PathVariable("id") Integer id) throws Exception{

        for(int i = 0; i<bookList.size(); i++){
            System.out.println(bookList.get(i).getId()+"id = " +id);
            if(id == bookList.get(i).getId()){
                return new ResponseEntity<>(bookList.get(i), HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(new Book(),HttpStatus.METHOD_NOT_ALLOWED);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()

    @DeleteMapping("/delete-book-by-id/{id}")
    public void deleteBookById(@PathVariable("id") Integer id) throws Exception {
        for(int i = 0; i<bookList.size(); i++){
            if(bookList.get(i).getId() == id) bookList.remove(bookList.get(i));
        }
        System.out.println("No book found");
    }

    // get request /get-all-books
    // getAllBooks()

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    // delete request /delete-all-books
    // deleteAllBooks()

    @DeleteMapping("/delete-all-books")
    public  void deleteAllBooks(){
        bookList.clear();
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()


    // localhost:8080/books/get-books-by-author/"chulbul"
    @GetMapping("/get-books-by-author")
        public ResponseEntity<Book> getBooksByAuthor(@RequestParam("author") String author){

        for(int i = 0; i<bookList.size(); i++){
            System.out.println(bookList.get(i).getAuthor()+" author "+author);
            if(bookList.get(i).getAuthor().equals(author))
            {

                return new ResponseEntity<>(bookList.get(i), HttpStatus.OK);
            }
        }

        System.out.println("hi");
        return new ResponseEntity<>(new Book(), HttpStatus.METHOD_NOT_ALLOWED);
        }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()

    @GetMapping("/get-books-by-genre")
    public  ResponseEntity<Book> getBooksByGenre(@RequestParam("genre") String genre){
       for(int i = 0; i<bookList.size(); i++){
           System.out.println(bookList.get(i).getGenre()+" author "+genre);
           if(bookList.get(i).getGenre().equals(genre))
           {

               return new ResponseEntity<>(bookList.get(i), HttpStatus.OK);
           }
       }
        return new ResponseEntity<>(new Book(), HttpStatus.OK);
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hi(){
        return new ResponseEntity<>("hi Chulubl ji", HttpStatus.OK);
    }

}
