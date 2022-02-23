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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public ResponseEntity<NewBookResponseDto> save(@RequestBody @Valid NewBookRequestDto newBookRequestDto) {
        log.info("newBook request.");
        return new ResponseEntity<NewBookResponseDto>(bookService.newBook(newBookRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{code}")
    public ResponseEntity<UpdateBookResponseDto> update(@RequestBody @Valid UpdateBookRequestDto updateBookRequestDto, @PathVariable String code) {
        log.info("updateBook request.");
        return new ResponseEntity<UpdateBookResponseDto>(bookService.updateBookStockAndPrice(code, updateBookRequestDto), HttpStatus.CREATED);
    }

}
