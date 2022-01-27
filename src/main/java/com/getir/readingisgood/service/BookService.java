package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.NewBookRequestDto;
import com.getir.readingisgood.dto.NewBookResponseDto;
import com.getir.readingisgood.dto.UpdateBookRequestDto;
import com.getir.readingisgood.dto.UpdateBookResponseDto;

public interface BookService {


    NewBookResponseDto newBook(NewBookRequestDto newBookRequestDto);

    UpdateBookResponseDto updateBookStockAndPrice(UpdateBookRequestDto updateBookRequestDto);
}
