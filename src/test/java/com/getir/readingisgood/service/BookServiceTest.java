package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.NewBookRequestDto;
import com.getir.readingisgood.dto.NewBookResponseDto;
import com.getir.readingisgood.dto.UpdateBookRequestDto;
import com.getir.readingisgood.dto.UpdateBookResponseDto;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author UmutBayram
 */
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private NewBookRequestDto newBookRequestDto;
    private Book book;
    private UpdateBookRequestDto updateBookRequestDto;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository);
        newBookRequestDto = NewBookRequestDto.builder()
                .code("test")
                .name("test")
                .price(new BigDecimal(1))
                .stock(10L)
                .build();

        book = Book.builder()
                .code("test")
                .name("test")
                .price(new BigDecimal(1))
                .stock(10L)
                .build();

        updateBookRequestDto = UpdateBookRequestDto.builder()
                .code("test")
                .price(new BigDecimal(1))
                .stock(10L)
                .build();
    }

    @Test
    void testNewBook() {
        when(bookRepository.insert(any(Book.class))).thenReturn(book);
        NewBookResponseDto newBookResponseDto = bookService.newBook(newBookRequestDto);
        assertNotNull(newBookResponseDto);
        assertEquals(newBookResponseDto.getCode(), book.getCode());
    }


    @Test
    void testUpdateBookStockAndPrice() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookRepository.findByCode(anyString())).thenReturn(Optional.ofNullable(book));
        UpdateBookResponseDto updateBookResponseDto = bookService.updateBookStockAndPrice(updateBookRequestDto);
        assertNotNull(updateBookResponseDto);
        assertEquals(updateBookResponseDto.getCode(), book.getCode());
        assertEquals(updateBookResponseDto.getStock(), book.getStock());
        assertEquals(updateBookResponseDto.getPrice(), book.getPrice());
    }
}