package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.NewBookRequestDto;
import com.getir.readingisgood.dto.NewBookResponseDto;
import com.getir.readingisgood.dto.UpdateBookRequestDto;
import com.getir.readingisgood.dto.UpdateBookResponseDto;
import com.getir.readingisgood.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    private BookController bookController;

    @Mock
    private BookService bookService;

    private NewBookRequestDto newBookRequestDto;
    private UpdateBookRequestDto updateBookRequestDto;
    private NewBookResponseDto newBookResponseDto;
    private UpdateBookResponseDto updateBookResponseDto;

    @BeforeEach
    void setUp() {
        bookController = new BookController(bookService);
        updateBookResponseDto = UpdateBookResponseDto.builder().build();
        updateBookRequestDto = UpdateBookRequestDto.builder().build();
        newBookResponseDto = NewBookResponseDto.builder().build();
        newBookRequestDto = NewBookRequestDto.builder().build();
    }

    @Test
    void testNewBook() {
        Mockito.when(bookService.newBook(any(NewBookRequestDto.class))).thenReturn(newBookResponseDto);
        ResponseEntity<NewBookResponseDto> newBookResponseDto = bookController.newBook(newBookRequestDto);
        Assertions.assertNotNull(newBookResponseDto);
    }

    @Test
    void testUpdateBook() {
        Mockito.when(bookService.updateBookStockAndPrice(any(UpdateBookRequestDto.class))).thenReturn(updateBookResponseDto);
        ResponseEntity<UpdateBookResponseDto> updateBookResponseDtoResponseEntity = bookController.updateBook(updateBookRequestDto);
        Assertions.assertNotNull(updateBookResponseDtoResponseEntity);
    }

}