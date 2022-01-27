package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.NewBookRequestDto;
import com.getir.readingisgood.dto.NewBookResponseDto;
import com.getir.readingisgood.dto.UpdateBookRequestDto;
import com.getir.readingisgood.dto.UpdateBookResponseDto;
import com.getir.readingisgood.service.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author UmutBayram
 */
@Log4j2
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/newBook")
    public ResponseEntity<NewBookResponseDto> newBook(@RequestBody @Valid NewBookRequestDto newBookRequestDto) {
        log.info("newBook request.");
        return new ResponseEntity<NewBookResponseDto>(bookService.newBook(newBookRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateBookResponseDto> updateBook(@RequestBody @Valid UpdateBookRequestDto updateBookRequestDto) {
        log.info("updateBook request.");
        return new ResponseEntity<UpdateBookResponseDto>(bookService.updateBookStockAndPrice(updateBookRequestDto), HttpStatus.CREATED);
    }

}
