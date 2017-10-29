package org.semiworld.hellospring.controller;

import org.semiworld.hellospring.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {
    List<Book> bookList = new ArrayList<>(Arrays.asList(
            new Book(1, "Yaprak Dökümü", "Reşat Nuri Güntekin", 300),
            new Book(2, "Aşk", "Elif Şafak", 200),
            new Book(3, "Piraye", "Elif Şafak", 400)
    ));

    @GetMapping(value = "/books")
    public List<Book> getBookList() {
        return bookList;
    }

    @GetMapping(value = "/books/{id}")
    public Book getBook(@PathVariable int id) {
        return bookList.stream().filter(t -> t.getId() == id).findFirst().get();
    }

    @PostMapping(value = "/books")
    public String saveBook(@RequestBody Book book) {
        bookList.add(book);
        return book.getName() + " successfully added!";
    }

    @PutMapping(value = "/books/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == id) {
                bookList.set(i, book);
                return book.getName() + " successfully updated!";
            }
        }
        return "Something went wrong. It is not updated!";
    }

    @DeleteMapping(value = "/books/{id}")
    public String deleteBook(@PathVariable int id) {
        Book book = bookList.stream().filter(b -> b.getId() == id).findFirst().get();
        bookList.remove(book);
        return book.getName() + " succesfully deleted!";
    }
}
