package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.NewBookRequestDto;
import com.getir.readingisgood.dto.NewBookResponseDto;
import com.getir.readingisgood.dto.UpdateBookRequestDto;
import com.getir.readingisgood.dto.UpdateBookResponseDto;
import com.getir.readingisgood.exception.ExceptionTypeEnum;
import com.getir.readingisgood.exception.NotFoundException;
import com.getir.readingisgood.mapper.BookMapper;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author UmutBayram
 */
@Log4j2
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public NewBookResponseDto newBook(NewBookRequestDto newBookRequestDto) {
        Book book = BookMapper.INSTANCE.bookToNewBookRequestDto(newBookRequestDto);
        book = bookRepository.insert(book);
        log.info("new book created. ");
        return BookMapper.INSTANCE.newBookResponseDtoToBook(book);
    }

    @Override
    @Transactional
    public UpdateBookResponseDto updateBookStockAndPrice(UpdateBookRequestDto updateBookRequestDto) {
        Optional<Book> bookOptional = bookRepository.findByCode(updateBookRequestDto.getCode());
        Book book = bookOptional.orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.NOT_FOUND_EXCEPTION.getCode(), "Data not found : " + updateBookRequestDto.getCode()));
        book.setPrice(updateBookRequestDto.getPrice());
        book.setStock(updateBookRequestDto.getStock());
        book = bookRepository.save(book);
        log.info("book updated. ");
        return BookMapper.INSTANCE.updateBookResponseDtoToBook(book);
    }
}
