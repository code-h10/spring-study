package com.spring.oauth2.service;

import com.spring.oauth2.domain.Book;
import com.spring.oauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;




    public Book getBook(Book book) throws Exception {

        return book;

//        return new BookDto();
    }



}
