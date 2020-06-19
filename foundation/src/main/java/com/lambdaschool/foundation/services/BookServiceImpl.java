package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookrepos;

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();

        bookrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Override
    public Book save(Book book) {
        Book newBook = new Book();

        if (book.getBookid() != 0) {
            newBook.setBookid(book.getBookid());
        }

        newBook.setBooktitle(book.getBooktitle());
        newBook.setISBN(book.getISBN());
        newBook.setCopy(book.getCopy());
        newBook.setSection(book.getSection());

        return bookrepos.save(newBook);
    }

    @Transactional
    @Override
    public void delete(long bookid) {
         bookrepos.findById(bookid)
                 .orElseThrow(() -> new EntityNotFoundException("Book with " + bookid + " not found..."));
         bookrepos.deleteById(bookid);
    }
}
