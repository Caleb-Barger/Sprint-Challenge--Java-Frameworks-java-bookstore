package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.models.ErrorDetail;
import com.lambdaschool.foundation.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @ApiOperation(value = "returns all the books",
    response = Book.class,
    responseContainer = "List")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks() {
        List<Book> myBooks = bookService.findAll();
        return  new ResponseEntity<>(myBooks, HttpStatus.OK);
    }

    @ApiOperation(value = "deletes given book",
    response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
        message = "Book Found",
        response = Book.class), @ApiResponse(code = 404,
        message = "Book not found",
        response = ErrorDetail.class)})
    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity<?> deleteBookById(
            @ApiParam(value = "bookid",
                required = true,
                example = "3")
            @PathVariable
                long id)  {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
