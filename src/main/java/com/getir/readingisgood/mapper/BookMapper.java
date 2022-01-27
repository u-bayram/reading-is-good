package com.getir.readingisgood.mapper;

import com.getir.readingisgood.dto.NewBookRequestDto;
import com.getir.readingisgood.dto.NewBookResponseDto;
import com.getir.readingisgood.dto.UpdateBookResponseDto;
import com.getir.readingisgood.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookToNewBookRequestDto(NewBookRequestDto newBookRequestDto);

    NewBookResponseDto newBookResponseDtoToBook(Book book);

    UpdateBookResponseDto updateBookResponseDtoToBook(Book book);
}
