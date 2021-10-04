package com.spring.oauth2.mapper;

import com.spring.oauth2.domain.Book;
import com.spring.oauth2.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper{

    @Mapping(target = "create_date", source = "createDate")
    @Mapping(target = "update_date", source = "updateDate")
    BookDto bookToBookDto(Book book);

    @Mapping(target = "createDate", source = "create_date")
    @Mapping(target = "updateDate", source = "update_date")
    Book bookDtoToBook(BookDto bookDto);



}
