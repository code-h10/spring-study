package com.spring.oauth2.controller;



import com.spring.common.dto.Response;
import com.spring.oauth2.domain.Book;
import com.spring.oauth2.dto.BookDto;
import com.spring.oauth2.mapper.BookMapper;
import com.spring.oauth2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/oauth2")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping(value = "/login")
    public Response login() {

        return Response.success(200,  false, "테스트 라네");
    }


    @GetMapping(value = "/test1")
    public Book test1(@RequestBody @Validated BookDto bookDto) {

        Book book = null;

        try {
            book = loginService.getBook(bookMapper.bookDtoToBook(bookDto));
        } catch (Exception ex){

        }

        return book;
    }

    @GetMapping(value = "/test2")
    public List<BookDto> test2(@RequestBody BookDto bookDto){

        List<BookDto> bookDtoList = null;

        try {
//            bookDtoList = loginService.getBookList();
        } catch (Exception ex){

        }

        return bookDtoList;
    }
}
